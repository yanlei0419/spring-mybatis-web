package com.seeker.common.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seeker.common.base.entity.Page;
import com.seeker.common.util.ExcelUtil;
import com.seeker.common.util.OperateLogUtil;
import com.seeker.common.util.SysConstant;
import com.seeker.common.util.VeUtil;

public class BaseController {
	private Logger l=LoggerFactory.getLogger(BaseController.class);
	private String operateModuleName;//模块名称
	private String operateContent;//条件
	private String operateRemark;//操作内容
	/**
	 * @param request
	 * @param response
	 * @param list 对象集合
	 * @param fileName 文件名称
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,List list,String fileName) throws Exception {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");  
		String title=request.getParameter("excel_titles");
		String field=request.getParameter("excel_fields");
		String sheetName=request.getParameter("sheetName");
        String rootpath = request.getSession().getServletContext().getRealPath("/");  
        if(VeUtil.isNull(title)||VeUtil.isNull(field)){
        	return;
        }
        if(VeUtil.isNull(sheetName)){
        	sheetName="Sheet1";
        }
        String[] titles=title.split(",");
        String[] fields=field.split(",");

        
		String path = ExcelUtil.getExcelPath(list, fileName, rootpath, titles, fields,sheetName);
		
		String operateContent="excel 标题名称  "+Arrays.deepToString(titles)+"   生成excel字段名称"+Arrays.deepToString(fields) +"   生成文件名"+fileName+">>>>>生成路径"+path;
		this.setOperateContent(operateContent);
		this.saveExcelSysLog(request, SysConstant.EXCEL);
		l.info(operateContent);
		PrintWriter out = response.getWriter();
		l.debug(path);
		out.print(path);
		out.flush();
		out.close();
		return ;
    }

	/**
	 * @param request
	 * @param response
	 * @param titles 标题
	 * @param fields 对象属性
	 * @param list 对象集合
	 * @param fileName 文件名称
	 * @param sheetName sheet名称
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String[] titles,String[] fields,List list,String fileName,String sheetName) throws Exception {  
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");  
        String rootpath = request.getSession().getServletContext().getRealPath("/");  
		String path = ExcelUtil.getExcelPath(list, fileName, rootpath, titles, fields,sheetName);

		String operateContent="excel 标题名称  "+Arrays.deepToString(titles)+"   生成excel字段名称"+Arrays.deepToString(fields) +"		生成文件名"+fileName;
		this.setOperateContent(operateContent);
		this.saveExcelSysLog(request, SysConstant.EXCEL);
		l.info(operateContent);
		l.info("path>>-=-=-=-=-=-=-=-="+path);
		PrintWriter out = response.getWriter();
		out.print(path);
		out.flush();
		out.close();
    }
	
	public void setFilePath(HttpServletRequest req,Page page) {
		String baseProjectPath=req.getSession().getServletContext().getRealPath("/");
		page.setBaseProjectPath(baseProjectPath);
		
	}
	
	/**
	 * 分页导出
	 * @param request
	 * @param response
	 * @param list 数据
	 * @param fileName   文件名称
	 * @param titles  标题数组 
	 * @param field	数值数组
	 * @param sheetNames  标签页数组
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void exportSheetExcel(HttpServletRequest request, HttpServletResponse response,List list,String fileName,List<String[]> titles,List<String[]> fields,String[] sheetNames) throws Exception {  
		response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");  
        String rootpath = request.getSession().getServletContext().getRealPath("/");  
        String path = ExcelUtil.getSheetExcelPath(list, fileName, rootpath, titles, fields,sheetNames);
        
        String strTitles="";
        String strFields="";
        for (int i = 0; i < titles.size(); i++) {
        	strTitles+=Arrays.deepToString(titles.get(i));
		}
        for (int i = 0; i < fields.size(); i++) {
        	strFields+=Arrays.deepToString(fields.get(i));
        }
       
        
        String operateContent="excel 标题名称  "+strTitles+"   生成excel字段名称"+strFields+"		生成文件名"+fileName+"  sheetName"+Arrays.deepToString(sheetNames);
		this.setOperateContent(operateContent);
		this.saveExcelSysLog(request, SysConstant.EXCEL);
		l.info(operateContent);
		l.info("path>>>>>>>>>>>>>>>>>>>"+path);
		PrintWriter out = response.getWriter();
		out.print(path);
		l.debug(path);
		out.flush();
		out.close();
	}
	
	public void saveSysLog(HttpServletRequest req,String type,String content,String remark){
		try {
			OperateLogUtil.saveSysLog(req, this.getOperateModuleName(),type, content,remark);
		} catch (Exception e) {
			l.error("日志保存出现错误",e);
		}
	}
	
	public void saveExcelSysLog(HttpServletRequest req,String type){
		saveSysLog(req, type, this.getOperateContent(),this.getOperateRemark());
	}

	public void print(HttpServletResponse resp,String result) throws IOException{
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	
	public void printJson(HttpServletResponse resp,String result) throws IOException{
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PrintWriter out=resp.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	public String getOperateModuleName() {
		return operateModuleName;
	}

	public void setOperateModuleName(String operateModuleName) {
		this.operateModuleName = operateModuleName;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getOperateRemark() {
		return operateRemark;
	}

	public void setOperateRemark(String operateRemark) {
		this.operateRemark = operateRemark;
	}
	
	
	
	public Object initPage(Page page) {
		return mysqlInitPage(page);
	}
	public Object oracleInitPage(Page page) {
		int rows, p;
		try {
			rows = page.getRows();
		} catch (Exception e) {
			rows = 10;
		}
		try {
			p = page.getPage();
		} catch (Exception e) {
			p = 1;
		}
		page.setBegin(rows * (p - 1) + 1);
		page.setEnd(rows * p);
		return page;
	}
	public Object mysqlInitPage(Page page) {
		int rows, p;
		try {
			rows = page.getRows();
		} catch (Exception e) {
			rows = 10;
			page.setRows(rows);
		}
		try {
			p = page.getPage();
		} catch (Exception e) {
			p = 1;
		}
		page.setBegin(rows * (p - 1));
		return page;
	}
	
	
}