package org.seeker.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.seeker.common.excel.entity.ExcelMergeCell;
import org.seeker.common.excel.entity.ExcelMergeCoordinate;
import org.seeker.common.excel.entity.ExcelMergeRow;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SuppressWarnings("rawtypes")
public class ExcelUtil {
	private static Logger l=LoggerFactory.getLogger(ExcelUtil.class);
	private static String pattern="YYYY-MM-dd HH:mm:ss";
	private static SimpleDateFormat sdf=new SimpleDateFormat(pattern);

	private final static short TitleRowHeight=640;
	private final static short TitleCellWidth=300;

	private final static short DataRowHeight=640;
	private final static short DataeCellWidth=300;

	private final static String TitleFontName="微软雅黑";
	private final static short TitleFontHeight=280;

	private final static String FontName="微软雅黑";
	private final static short FontHeight=200;


	/**
	 * 生成excel文档对象
	 * @param titles
	 * @param fields
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static Workbook exportExcelNew(List<ExcelMergeRow> mergeTitle, List<ExcelMergeRow> mergeFoot, String[] titles, String[] fields, List list, String sheetName) throws Exception {
		// 创建sheet对象
		HSSFWorkbook wb =  new HSSFWorkbook();
		return exportSheetExcelNew(wb,mergeTitle,mergeFoot,titles,fields,list,sheetName);
	}

	private static Workbook exportSheetExcelNew(HSSFWorkbook wb, List<ExcelMergeRow> mergeTitle, List<ExcelMergeRow> mergeFoot, String[] titles, String[] fields, List list, String sheetName) throws Exception {
		CellStyle style=getTitleStyle(wb);
		int rowNum=0;
		Sheet sheet=wb.createSheet(sheetName);
		//生成表头
		handleMergeData(mergeTitle,sheet,style,rowNum);
		rowNum+=mergeTitle.size();
		style=getDataStyle(wb);

		//数据标题
		handleExcelBodyTitle(titles,sheet,style,rowNum);
		rowNum++;
		//数据
		handleExcelBody(fields,list,sheet,style,rowNum);

		handleMergeData(mergeFoot,sheet,style,rowNum);

		dataSizeColumn(sheet,titles);




		return wb;

	}

	private static void handleExcelBody(String[] fields, List list, Sheet sheet, CellStyle style, int rowNum) throws Exception{
		Row row=null;

		// 循环写入行数据
		for (int i = 0; i < list.size(); i++) {
			Object objData=list.get(i);
			row = sheet.createRow(rowNum+i);
			row.setHeight(DataRowHeight);
			for (int j = 0; j < fields.length; j++) {
				Cell tempCell=row.createCell(j);
				if("".equals(fields[j])){
					tempCell.setCellValue(i+1);//序号
				}else{
					handleRefletFieldData(objData.getClass().getDeclaredMethod("get" + VeUtil.captureName(fields[j])), sheet, row, i, objData, j, tempCell);
				}
			}
		}
	}

	private static void handleRefletFieldData(Method declaredMethod, Sheet sheet, Row row, int i, Object objData, int j, Cell tempCell) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method m= declaredMethod;
		Object val=m.invoke(objData);
//					if(val==null){
//						tempCell.setCellValue("");
//					}else
		if(val instanceof Integer){
            int intVal= (Integer) val;
            tempCell.setCellValue(intVal);//
        }else if(val instanceof Float){
            float intVal= (Float) val;
            tempCell.setCellValue(intVal);//
        }else if(val instanceof Double){
            double intVal= (Double) val;
            tempCell.setCellValue(intVal);//
        }else if(val instanceof Long){
            long intVal= (Long) val;
            tempCell.setCellValue(intVal);//
        }
		String textVal=null;
		if(val instanceof Date){
            Date date=(Date) val;
            textVal=sdf.format(date);
            tempCell.setCellValue(textVal);//
        }else if(val instanceof byte[]){//图片
            //有图片时,设置行高为60px
            row.setHeightInPoints(60);
            sheet.setColumnWidth(j, (int) (35.7*80));
            byte[] bsVal= (byte[]) val;
            HSSFClientAnchor anchor=new HSSFClientAnchor(0,0,1023,255,(short)6,i,(short) 6,i);
            anchor.setAnchorType(2);
//						patriarch.createPicture(anchor,wb.addPicture(bsVal,HSSFWorkbook.PICTURE_TYPE_JPEG));
        }else{
            if(val!=null){
                textVal=val.toString();
            }else{
                tempCell.setCellValue("");
            }
        }

		if(textVal!=null){
            Pattern p=Pattern.compile("^//d+(//.//d+)?$");
            Matcher matcher=p.matcher(textVal);
            if(matcher.matches()){
                tempCell.setCellValue(Double.parseDouble(textVal));
            }else{
                HSSFRichTextString rts = new HSSFRichTextString(textVal);
//							rts.applyFont();
                tempCell.setCellValue(rts);
            }
        }
	}

	private static void dataSizeColumn(Sheet sheet, String[] val) {
		for (int i = 0; i < val.length; i++) {
			sheet.setColumnWidth(i,val[i].getBytes().length*256*2);
		}
	}
	private static void dataSizeColumn(Sheet sheet, int len) {
		for (int i = 0; i < len; i++) {
			sheet.autoSizeColumn(i,true);
		}
	}

	private static void handleExcelBodyTitle(String[] titles, Sheet sheet, CellStyle style, int rowNum) {
		Row row = sheet.createRow(rowNum);
		Cell cell =null;
		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style); // 样式，居中
		}
	}

	private static void handleMergeData(List<ExcelMergeRow> list, Sheet sheet, CellStyle style, int startRowNum) {
		if(VeUtil.isEmptyList(list)){
			return ;
		}
		List<ExcelMergeCoordinate> coor=new ArrayList<>();
		ExcelMergeCoordinate num=null;
		ExcelMergeCell temp=null;
		List<ExcelMergeCell>  cells=null;
		int cellSNum=0;
		int cellENum=0;
		int rowNum=0;

		for (int i = 0; i <list.size() ; i++) {
			cellSNum=0;
			cellENum=0;
			rowNum=startRowNum+i;
			Row row=sheet.createRow(rowNum);

			row.setHeight(TitleRowHeight);
			cells=list.get(i).getList();

			for (int j = 0; j < cells.size(); j++) {
				temp=cells.get(j);
				Cell cell=row.createCell(cellSNum);
				cellENum=cellSNum+temp.getCell()-1;
				num=new ExcelMergeCoordinate(rowNum,rowNum+temp.getRow()-1,cellSNum,cellENum);
				cellSNum=cellENum+1;


				style.setVerticalAlignment(temp.getVertical_align());
				style.setAlignment(temp.getText_align());

				coor.add(num);
			}
		}
		mergeCell(sheet,coor);

	}

	private static void mergeCell(Sheet sheet, List<ExcelMergeCoordinate> list) {
		for (ExcelMergeCoordinate e: list) {
			mergeCell(sheet,e.getStartRow(),e.getEndRow(),e.getStartCell(),e.getEndCell());
		}

	}

	private static void mergeCell(Sheet sheet, int startRow, int endRow, int startCell, int endCell) {
		CellRangeAddress cra=new CellRangeAddress(startRow,endRow,startCell,endCell);
		sheet.addMergedRegion(cra);
	}

	private static CellStyle getDataStyle(HSSFWorkbook wb) {
		CellStyle style=wb.createCellStyle();
		Font f=wb.createFont();
//		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		f.setFontName(FontName);
		f.setFontHeight(FontHeight);
		style.setFont(f);

		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER);
//		style.setWrapText(true);//是否自动换行
		return style;
	}

	private static CellStyle getTitleStyle(HSSFWorkbook wb) {
		CellStyle style=wb.createCellStyle();
		Font f=wb.createFont();
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		f.setFontName(TitleFontName);
		f.setFontHeight(TitleFontHeight);
		style.setFont(f);

		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}


	/**
	 * 生成excel文档对象
	 * @param titles
	 * @param fields
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static Workbook exportExcelNew(String[] titles, String[] fields,List list,String sheetName) throws Exception {
		// 创建sheet对象
		HSSFWorkbook wb =  new HSSFWorkbook();
		return exportSheetExcelNew(wb,titles,fields,list,sheetName);
	}

	/**
	 * 生成标的头文件
	 * @param titles
	 * @param wb
	 * @param sheet
	 */
	private static void createExcelTitle(String[] titles, HSSFWorkbook wb, Sheet sheet) {
		// 添加表头
		Row row = sheet.createRow(0);
		Cell cell =null;
		row.setHeight((short) 540);

		CellStyle style = wb.createCellStyle(); // 样式对象
		// 设置单元格的背景颜色为淡蓝色
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    

		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(CellStyle.ALIGN_CENTER);// 水平
		style.setWrapText(true);// 指定当单元格内容显示不下时自动换行


		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 280);
		style.setFont(font);

		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style); // 样式，居中
			sheet.setColumnWidth(i, 20 * 256);
		}
		row.setHeight((short) 540);
	}
	
//	/**
//	 * 生成excel文档对象
//	 * @param titles
//	 * @param fields
//	 * @param list
//	 * @return
//	 * @throws Exception
//	 */
//	public static Workbook outAnalysisExcelNew(String[] titles, String[] fields,List list,String sheetName) throws Exception {
//		// 创建sheet对象
//		HSSFWorkbook wb =  new HSSFWorkbook();
//		Sheet sheet = (Sheet) wb.createSheet(sheetName);
//
//		Row row=null;
//		createExcelTitle(titles, wb, sheet);
//
//		CellStyle blue=excelHandleCell(wb, HSSFColor.PALE_BLUE.index);
//		CellStyle red=excelHandleCell(wb, HSSFColor.RED.index);
//		CellStyle yellow=excelHandleCell(wb, HSSFColor.YELLOW.index);
//		CellStyle white=excelHandleCell(wb, HSSFColor.WHITE.index);
//		Object ans =null;
//		// 循环写入行数据
//		for (int i = 0; i < list.size(); i++) {
//			Object objData=list.get(i);
//			Method getRowsType=objData.getClass().getDeclaredMethod("getRowsType");;
//			Object objRowsType=getRowsType.invoke(objData);
////			if(ans instanceof Analysis){//判断是否为Analysis 对象
////				getRowsType=ans.getClass().getDeclaredMethod("getRowsType");
////				objRowsType=getRowsType.invoke(ans);
////			}
//			int num=1;
//			if(null!=objRowsType){
//				num=Integer.valueOf(objRowsType.toString());
//			}
//			row = (Row) sheet.createRow(i + 1);
//			row.setHeight((short) 500);
//			for (int j = 0; j < fields.length; j++) {
//				Cell tempCell=row.createCell(j);
//				if("".equals(fields[j])){
//					tempCell.setCellValue(i+1);//序号
//				}else if("is_update".equals(fields[j])||"similarity_is_update".equals(fields[j])){
//					Method m=objData.getClass().getDeclaredMethod("get"+VeUtil.captureName(fields[j]));
//					Object obj=m.invoke(objData);
//					if("1".equals(obj.toString())){
//						tempCell.setCellValue("是");//修改
//					}else{
//						tempCell.setCellValue("否");//修改
//					}
//				}else{
////					System.out.println("get"+captureName(fields[j]));
//					Method m=objData.getClass().getDeclaredMethod("get"+VeUtil.captureName(fields[j]));
//					Object obj=m.invoke(objData);
//					if(null!=obj){
//						tempCell.setCellValue(obj.toString());//
//					}else{
//						tempCell.setCellValue("");
//					}
//				}
//				if(num%6==2){
//					tempCell.setCellStyle(blue);
//				}else if(num%6==4){
//					tempCell.setCellStyle(red);
//				}else if(num%6==0){
//					tempCell.setCellStyle(yellow);
//				}else{
//					tempCell.setCellStyle(white);
//				}
//			}
//		}
//		return wb;
//	}
	
	/**
	 * 获取行样式
	 * @param wb
	 * @param num
	 * @return
	 */
	private static CellStyle excelHandleCell(HSSFWorkbook wb,short num){
		CellStyle temp=wb.createCellStyle();
		temp.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
		temp.setAlignment(CellStyle.ALIGN_CENTER);// 水平
		temp.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		temp.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		temp.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		temp.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
		temp.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		temp.setFillForegroundColor(num);
		return temp;
	}
	
//	private static CellStyle handleCellColor(String color,HSSFWorkbook wb,CellStyle temp,int num){
//		int r = Integer.parseInt((color.substring(0,2)),16);   //转为16进制  
//		int g = Integer.parseInt((color.substring(2,4)),16);  
//		int b = Integer.parseInt((color.substring(4,6)),16);  
//		//自定义cell颜色  
//		HSSFPalette palette = wb.getCustomPalette();   
//		//这里的9是索引  
//		short index=(short) (num*100);
//		palette.setColorAtIndex(index, (byte) r, (byte) g, (byte) b);  
////		temp.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//		temp.setFillForegroundColor(index);
//
//		temp.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
//		temp.setAlignment(CellStyle.ALIGN_CENTER);// 水平
//		temp.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//
//		return temp;
//	}
//	
	/**
	 * 生成excel文件
	 * @param list 对象数组
	 * @param fileName 文件名称
	 * @param path 输出地址
	 * @param titles excel 标题 数组
	 * @param fields 输出那些对象属性
	 * @return 文件地址
	 * @throws Exception
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getExcelPath(List list, String fileName, String path, String[] titles, String[] fields,String sheetName) throws Exception, FileNotFoundException, IOException {
		Workbook wb = null;
		if(null==list||list.size()<=0){
			return null;
		}
		wb=exportExcelNew(titles,fields, list,sheetName);//普通excel生成
		File file=new File(path+File.separatorChar+SysConstant.DOWNLOAD+File.separatorChar+fileName+".xls");
//		if (!file.exists()) {
//			file.mkdirs();
//		}
		OutputStream stream =new FileOutputStream(file); 
		// 写入数据
		wb.write(stream);
		// 关闭文件流
		stream.close();
		return file.getPath();
	}
	
	
	public static String getSheetExcelPath(List list, String fileName, String path, List<String[]> titles,List<String[]> fields,String[] sheetNames) throws Exception, FileNotFoundException, IOException {
		// 创建sheet对象
		HSSFWorkbook wb =  new HSSFWorkbook();
		if(null==list||list.size()<=0||list.size()!=sheetNames.length){
			return null;
		}
		if(list.get(0) instanceof List){
			for (int i = 0; i < sheetNames.length; i++) {
				exportSheetExcelNew(wb,titles.get(i), fields.get(i), (List)list.get(i), sheetNames[i]);//生成Analysis对象excel  要求比较多 放在一起增加复杂度
			}
		}
		File file=new File(path+File.separatorChar+SysConstant.DOWNLOAD+File.separatorChar+fileName+".xls");
//		if (!file.exists()) {
//			file.mkdirs();
//		}
		OutputStream stream =new FileOutputStream(file); 
		// 写入数据
		wb.write(stream);
		// 关闭文件流
		stream.close();
		return file.getPath();
	}
	
	public static Workbook exportSheetExcelNew(HSSFWorkbook wb,String[] titles, String[] fields,List list,String sheetName) throws Exception {
		// 创建sheet对象
		HSSFSheet sheet = wb.createSheet(sheetName);

		Row row=null;
		createExcelTitle(titles, wb, sheet);
		HSSFPatriarch patriarch=sheet.createDrawingPatriarch();


		// 循环写入行数据
		for (int i = 0; i < list.size(); i++) {
			Object objData=list.get(i);
			row = (Row) sheet.createRow(i + 1);
			row.setHeight((short) 500);
			for (int j = 0; j < fields.length; j++) {
				Cell tempCell=row.createCell(j);
				if("".equals(fields[j])){
					tempCell.setCellValue(i+1);//序号
				}else{
					handleRefletFieldData(objData.getClass().getDeclaredMethod("get" + VeUtil.captureName(fields[j])), sheet, row, i, objData, j, tempCell);
				}

			}
		}
		return wb;
	}







	/**
	 *导入excel文件
	 */

    
	/**
	 * 获取excel文档创建WorkBook对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Workbook createWorkbook(File file) throws IOException {

		String fileName = file.getName();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			 InputStream is = new FileInputStream(file);
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
			return workbook;
		} catch (IOException e) {
			l.error(e.getMessage());
			return null;
		}
	}


	public static String getHandleCellValue(Sheet sheet, int row, int column, Row rc) {
		if (isMergedRow(sheet, row, column)) {
			return getMergedRegionValue(sheet, row, column).trim();
		} else {
			return getCellValue(rc.getCell(column)).trim();
		}
	}


	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}
		return null;
	}
	

	private static final DecimalFormat dfs = new DecimalFormat("0");//取消数字小数点


	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		
		cell.setCellType(Cell.CELL_TYPE_STRING);
		byte bytes[] = { (byte) 0xC2, (byte) 0xA0 };
		String UTFSpace = "";
		try {
			UTFSpace = new String(bytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String val = cell.getStringCellValue();
		if (val != null && !"".equals(val)) {
			val = val.replaceAll(UTFSpace, " ");
			return val;
		} else {
			return "";
		}

//		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
//			byte bytes[] = { (byte) 0xC2, (byte) 0xA0 };
//			String UTFSpace = "";
//			try {
//				UTFSpace = new String(bytes, "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			String val = cell.getStringCellValue();
//			if (val != null && !"".equals(val)) {
//				val = val.replaceAll(UTFSpace, " ");
//				return val;
//			} else {
//				return "";
//			}
//		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
//			return String.valueOf(cell.getBooleanCellValue());
//		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
//			return cell.getCellFormula();
//		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			return cell.getStringCellValue();//TODO 字符串问题
//			return cell.getStringCellValue();
//			return String.valueOf(cell.getNumericCellValue());
//		}
//		return "";
	}
	/**
	 * 判断合并了行
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private static boolean isMergedRow(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}
    
    
    
}
