<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
RequestDispatcher dispatcher = request.getRequestDispatcher("/frame/index.jsp"); 
dispatcher .forward(request, response);
%>