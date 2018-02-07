<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		
		<div class="arrowlistmenu">
			<div class="menuheader expandable openheader">
				<span class="normal_icon"></span>数据字典
			</div>
			<ul class="categoryitems">
				<li><a id="1-1" href="javascript:void(0);" onclick="javascript:onMenuClick('1-1','采集数据字典','${pageContext.request.contextPath}/jsp/dic/collection_dic/list.jsp');" target="frmright"><span class="text_slice">采集数据字典</span></a></li>
				<li><a id="1-2" href="javascript:void(0);" onclick="javascript:onMenuClick('1-2','档案数据字典','${pageContext.request.contextPath}/jsp/dic/archives_dic/list.jsp');" target="frmright"><span class="text_slice">档案数据字典</span></a></li>
				<li><a id="1-3" href="javascript:void(0);" onclick="javascript:onMenuClick('1-3','产品数据字典','${pageContext.request.contextPath}/jsp/dic/product_dic/list.jsp');" target="frmright"><span class="text_slice">产品数据字典</span></a></li>
				<li><a id="1-4" href="javascript:void(0);" onclick="javascript:onMenuClick('1-4','管理数据字典','${pageContext.request.contextPath}/jsp/dic/manage_dic/list.jsp');" target="frmright"><span class="text_slice">管理数据字典</span></a></li>
				<li><a id="1-5" href="javascript:void(0);" onclick="javascript:onMenuClick('1-5','采集与档案字典关系','${pageContext.request.contextPath}/jsp/dic/collection_ar_nexus/list.jsp');" target="frmright"><span class="text_slice">采集与档案字典关系</span></a></li>
				<li><a id="1-6" href="javascript:void(0);" onclick="javascript:onMenuClick('1-6','档案与产品字典关系','${pageContext.request.contextPath}/jsp/dic/product_ar_nexus/list.jsp');" target="frmright"><span class="text_slice">档案与产品字典关系</span></a></li>
			</ul>
		</div>
		<div class="arrowlistmenu">
			<div class="menuheader expandable openheader">
				<span class="normal_icon"></span>数据标准
			</div>
			<ul class="categoryitems">
				<li><a id="2-1" href="javascript:void(0);" onclick="javascript:onMenuClick('2-1','数据标准查询','${pageContext.request.contextPath}/jsp/datastandard/list.jsp');" target="frmright"><span class="text_slice">数据标准查询</span></a></li>
			</ul>
		</div>
	</div>
		<script>
			$(function() {
				jQuery.jCookie('expandable', '0c');//默认展开第一个二级菜单
			})
		</script>
</body>
</html>
