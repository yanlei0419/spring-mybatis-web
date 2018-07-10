<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.seeker.common.util.MenuUtil"%>
<%@page import="org.seeker.entity.Menu"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	List<Menu> list=MenuUtil.handleMenuByGroupName("普通管理员");
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
		
		<%
			for(Menu m1:list){
		%>
		<div class="arrowlistmenu">
			<div class="menuheader expandable openheader">
				<span class="normal_icon"></span><%=m1.getName() %>
			</div>
			<ul class="categoryitems">
		<%
		for(Menu m2:m1.getSons()){
		%>
				<li><a id="<%=m2.getId() %>" href="javascript:void(0);" onclick="javascript:onMenuClick('<%=m2.getId() %>','<%=m2.getName() %>','${pageContext.request.contextPath}<%=m2.getUrl() %>');"  target="frmright"><span class="text_slice"><%=m2.getName() %></span></a></li>
<%-- 				<li><a id="<%=m2.getId() %>" href="javascript:void(0);" onclick="javascript:onMenuClick('<%=m2.getId() %>','<%=m2.getName() %>','${pageContext.request.contextPath}<%=m2.getUrl() %>');" title="<%=m2.getName() %>" target="frmright"><span class="text_slice"><%=m2.getName() %></span></a></li> --%>
		<%
		}
		%>
			</ul>
		</div>
		<%
		}
		%>
		
		
	</div>
		<script>
			$(function() {
				jQuery.jCookie('expandable', '0c');//默认展开第一个二级菜单
			})
		</script>
</body>
</html>
