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
	int i=url.lastIndexOf("/");
	int j=url.lastIndexOf(".");
	if(i+1<j)
	name=url.substring(i+1,j);
}
name+=".xls";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'download.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
