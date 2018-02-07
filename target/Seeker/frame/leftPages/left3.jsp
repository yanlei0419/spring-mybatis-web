<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/plugins/bootstrap3.3.7/css/bootstrap.min.css"  />
	
	<style>
	</style>
	
	<script>
		function onMenuClick(menuId, menuName, url) {
			top.document.getElementById('currMenuId').value=menuId;
			top.window.frames["frmright"].addTab(menuId,menuName,url,1);
		}
	</script>

	<body leftFrame="true">
	
	
	
	
	
	
	
	
	<div id="scrollContent">
		<c:forEach items="${User.menus}" var="m1">
		<div class="arrowlistmenu">
			<div class="menuheader expandable openheader">
				<span class="normal_icon"></span>${m1.name}
			</div>
			<ul class="categoryitems">
				<c:forEach items="${m1.sons}" var="m2">
				<li><a id="${m2.id }" href="javascript:void(0);" onclick="javascript:onMenuClick('${m2.id }','${m2.name }','${pageContext.request.contextPath}${m2.url }?menuId=${m2.id }');" target="frmright"><span class="text_slice">${m2.name }</span></a></li>
<%-- 				<li><a id="${m2.id }" href="javascript:void(0);" onclick="javascript:onMenuClick('${m2.id }','${m2.name }','${pageContext.request.contextPath}${m2.url }?menuId=${m2.id }');" target="frmright" title="${m2.name }"><span class="text_slice">${m2.name }</span></a></li> --%>
				</c:forEach>
			</ul>
		</div>
		</c:forEach>
		
	</div>
		<script>
			$(function() {
				jQuery.jCookie('expandable', '0c');//默认展开第一个二级菜单
			})
		</script>
</body>
</html>
