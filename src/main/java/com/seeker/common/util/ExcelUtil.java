package com.seeker.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
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
	
	/**
	 * 生成excel文档对象
	 * @param titles
	 * @param fields
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static Workbook outExcelNew(String[] titles, String[] fields,List list,String sheetName) throws Exception {
		// 创建sheet对象
		HSSFWorkbook wb =  new HSSFWorkbook();
		Sheet sheet = (Sheet) wb.createSheet(sheetName);

		Row row=null;
		createExcelTitle(titles, wb, sheet);
		
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
					Method m=objData.getClass().getDeclaredMethod("get"+VeUtil.captureName(fields[j]));
					Object obj=m.invoke(objData);
					if(null!=obj){
						tempCell.setCellValue(obj.toString());//
					}else{
						tempCell.setCellValue("");
					}
				}
			}
		}
		return wb;
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
	
	/**
	 * 生成excel文档对象
	 * @param titles
	 * @param fields
	 * @param list
	 * @return
	 * @throws Exception
	 */

	public static Workbook outAnalysisExcelNew(String[] titles, String[] fields,List list,String sheetName) throws Exception {
		// 创建sheet对象
		HSSFWorkbook wb =  new HSSFWorkbook();
		Sheet sheet = (Sheet) wb.createSheet(sheetName);

		Row row=null;
		createExcelTitle(titles, wb, sheet);
		
		CellStyle blue=excelHandleCell(wb, HSSFColor.PALE_BLUE.index);
		CellStyle red=excelHandleCell(wb, HSSFColor.RED.index);
		CellStyle yellow=excelHandleCell(wb, HSSFColor.YELLOW.index);
		CellStyle white=excelHandleCell(wb, HSSFColor.WHITE.index);
//		Object ans =null;
		// 循环写入行数据
		for (int i = 0; i < list.size(); i++) {
			Object objData=list.get(i);
			Method getRowsType=objData.getClass().getDeclaredMethod("getRowsType");;
			Object objRowsType=getRowsType.invoke(objData);
//			if(ans instanceof Analysis){//判断是否为Analysis 对象
//				getRowsType=ans.getClass().getDeclaredMethod("getRowsType");
//				objRowsType=getRowsType.invoke(ans);
//			}
			int num=1;
			if(null!=objRowsType){
				num=Integer.valueOf(objRowsType.toString());
			}
			row = (Row) sheet.createRow(i + 1);
			row.setHeight((short) 500);
			for (int j = 0; j < fields.length; j++) {
				Cell tempCell=row.createCell(j);
				if("".equals(fields[j])){
					tempCell.setCellValue(i+1);//序号
				}else if("is_update".equals(fields[j])||"similarity_is_update".equals(fields[j])){
					Method m=objData.getClass().getDeclaredMethod("get"+VeUtil.captureName(fields[j]));
					Object obj=m.invoke(objData);
					if("1".equals(obj.toString())){
						tempCell.setCellValue("是");//修改
					}else{
						tempCell.setCellValue("否");//修改
					}
				}else{
//					System.out.println("get"+captureName(fields[j]));
					Method m=objData.getClass().getDeclaredMethod("get"+VeUtil.captureName(fields[j]));
					Object obj=m.invoke(objData);
					if(null!=obj){
						tempCell.setCellValue(obj.toString());//
					}else{
						tempCell.setCellValue("");
					}
				}
				if(num%6==2){
					tempCell.setCellStyle(blue);
				}else if(num%6==4){
					tempCell.setCellStyle(red);
				}else if(num%6==0){
					tempCell.setCellStyle(yellow);
				}else{
					tempCell.setCellStyle(white);
				}
			}
		}
		return wb;
	}
	
	/**
	 * 获取行样式
	 * @param wb
	 * @param num
	 * @param temp
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
		wb=outExcelNew(titles,fields, list,sheetName);//普通excel生成
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
//		return file.getPath().replaceAll("\\\\", "/");
	}
	
	
	public static String getSheetExcelPath(List list, String fileName, String path, List<String[]> titles,List<String[]> fields,String[] sheetNames) throws Exception, FileNotFoundException, IOException {
		// 创建sheet对象
		HSSFWorkbook wb =  new HSSFWorkbook();
		if(null==list||list.size()<=0||list.size()!=sheetNames.length){
			return null;
		}
		if(list.get(0) instanceof List){
			for (int i = 0; i < sheetNames.length; i++) {
				outSheetExcelNew(wb,titles.get(i), fields.get(i), (List)list.get(i), sheetNames[i]);//生成Analysis对象excel  要求比较多 放在一起增加复杂度
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
//		return file.getPath().replaceAll("\\\\", "/");
	}
	
	public static Workbook outSheetExcelNew(HSSFWorkbook wb,String[] titles, String[] fields,List list,String sheetName) throws Exception {
		// 创建sheet对象
		Sheet sheet = (Sheet) wb.createSheet(sheetName);

		Row row=null;
		createExcelTitle(titles, wb, sheet);
		
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
					Method m=objData.getClass().getDeclaredMethod("get"+VeUtil.captureName(fields[j]));
					Object obj=m.invoke(objData);
					if(null!=obj){
						tempCell.setCellValue(obj.toString());//
					}else{
						tempCell.setCellValue("");
					}
				}
			}
		}
		return wb;
	}

	
	
	
	
	
	

    
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
