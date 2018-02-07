<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<jsp:include page="/jsp/include/doctype.jsp" flush="true" />
<html>
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/plugins/bootstrap3.3.7/css/bootstrap.min.css"  />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
	<jsp:include page="/jsp/include/jquery.jsp"  flush = "true" />
	<style type="text/css">
	.b{border: 1px solid black;}
	</style>
	<script type="text/javascript">
		var basePath="${pageContext.request.contextPath}";

	</script>

</head>
<body class="bg-primary">
<div class="container" style="width: 600px; height: 400px;margin: 100px auto; ">
   <div class="jumbotron " style="padding-left: 100px; padding-right: 100px;">
   		 <h1 style="text-align: center; margin: 20px 0px 40px 0px;color: black;" >没有访问权限</h1> 
	</div>
</div>
</body>
</html>