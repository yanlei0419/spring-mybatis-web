<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/include/doctype.jsp" flush="true" />
<html>
<head>
<meta charset="UTF-8">
<title>无权操作</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap3.3.7/css/bootstrap.min.css" />
<jsp:include page="/jsp/include/jquery.jsp" flush="true" />
<style type="text/css">
.b {
	border: 1px solid black;
}
</style>
<script type="text/javascript">
		var basePath="${pageContext.request.contextPath}";

	</script>

</head>
<body class="">
	<div class="container" style="margin: 50px auto; padding:padding-left: 100px; padding-right: 100px; ">
		<div class="jumbotron " style=" margin-left: 90px;padding-left: 100px; padding-right: 100px;">
		 	<h1>无权操作。</h1>
<!-- 			<div class="alert alert-warning">无权操作。</div> -->
			<div class="alert alert-danger" style="margin: 50px 0px">对不起，您无权执行此操作 ...</div>
			<div class="alert alert-danger" style="margin: 50px 0px">${requestScope['SPRING_SECURITY_403_EXCEPTION'].message}</div>
			<div style="text-align: left: ;">
				<a href="javascript:top.window.location.href='${pageContext.request.contextPath}/login.jsp'" class="btn btn-warning btn-lg btn-block">&nbsp;回&nbsp;&nbsp;&nbsp;&nbsp;退&nbsp;</a> 
			</div>
		<h1>&nbsp;</h1>
		</div>
	</div>
</body>
</html>
