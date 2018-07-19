<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/easyui/themes/icon.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/easyui/jquery.easyui.min.js"></script>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easyui-1.5.1/jquery.easyui.min.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/easyui/easyui.extend.js"></script>
	<script type="text/javascript" >
		function fillsize(percent) {
			var bodyWidth = document.body.clientWidth;
			return (bodyWidth - 90) * percent;
		}
		var pageList = [1,"20","50", "100", "200","300", "500" ];
		var pageSize=20;
		var width=$(window).width();
		var height=$(window).height()-115;
		if(height==null||height==0||height==undefined||height==""){
			height='600';
		}
		// if(height<600){
		// 	height='auto';
		// }
		if(width==null||width==0||width==undefined||width==""){
			width='100%';
		}
		width='100%';
	</script>
