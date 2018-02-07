<%@page import="com.zx.datastandard.common.util.VeUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.jspsmart.upload.*,java.text.SimpleDateFormat,java.io.File"%>

<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String url=request.getParameter("url");
Date date=new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String time=sdf.format(date);
String name="download";

if(null!=url&&!"".equals(url)){
	name=VeUtil.handleFileNameByURL(url);
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  <body>
    <%
     int excel=url.indexOf("download"); 
     url=url.substring(excel);    
     SmartUpload smartUpload=new SmartUpload(); 
     smartUpload.initialize(pageContext);
     
	 smartUpload.setContentDisposition(null);
     smartUpload.downloadFile(url,"application/x-msdownload",java.net.URLEncoder.encode(name,"UTF-8"));
     
     out.clear(); 
     out = pageContext.pushBody();
   %>

  </body>
</html>
