<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--框架必需start-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/frame/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/frame/js/framework.js"></script>
	<link href="${pageContext.request.contextPath}/frame/css/import_basic.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" />
	<!--框架必需end-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/frame/js/nav/ddaccordion.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/frame/js/text/text-overflow.js"></script>
	
	<style>
		a {behavior: url(${pageContext.request.contextPath}/frame/js/method/focus.htc)}
		.categoryitems span {width: 160px;}
		 .arrowlistmenu .menuheader {
			color: #03509e;
			background: white url(${pageContext.request.contextPath}/frame/skins/sky/tab/titlebar.jpg) repeat-x center left;
			padding: 4px 0 4px 10px;
			cursor: hand;
			cursor: pointer;
			border: solid 1px #b7cce7;
			height: 16px !important;
			position: relative;
			text-indent: 15px;
		}
		
		.arrowlistmenu .openheader {
			background-image: url(${pageContext.request.contextPath}/frame/skins/sky/tab/titlebar-active.jpg);
			color: white;
			height: 16px !important;
			border: 1px solid #008eda !important;
		}
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
				<li><a id="${m2.id }" href="javascript:void(0);" onclick="javascript:onMenuClick('${m2.id }','${m2.name }','${pageContext.request.contextPath}${m2.url }?menuId=${m2.id }');" target="frmright"  ><span class="text_slice">${m2.name }</span></a></li>
<%-- 				<li><a id="${m2.id }" href="javascript:void(0);" onclick="javascript:onMenuClick('${m2.id }','${m2.name }','${pageContext.request.contextPath}${m2.url }?menuId=${m2.id }');" target="frmright"  title="${m2.name}"><span class="text_slice">${m2.name }</span></a></li> --%>
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
