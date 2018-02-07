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
   		 <h1 style="text-align: center; margin: 20px 0px 40px 0px;color: black;" >登&nbsp;&nbsp;&nbsp;录</h1> 
	    <form class="bs-example bs-example-form" role="form" method="post" id="myform">
	        <div class="input-group input-group-lg  ">
	            <span class="input-group-addon">帐户</span>
	            <input type="text" class="form-control" placeholder="帐&nbsp;&nbsp;户" id="username" name="username"/>
	        </div>
	        <br>
	        <div class="input-group  input-group-lg ">
	            <span class="input-group-addon">密码</span>
	            <input type="password" class="form-control"  placeholder="密&nbsp;&nbsp;码" id="password" name="password"/>
	        </div>
	        <div class="input-group  well-lg text-center">
	        	<a class="btn btn-primary btn-lg " role="button" style="width: 320px; margin: 20px 0px 0px 0px; " onclick="javascript:login()">登录</a>
	        </div>
	    </form>
	</div>
</div>
</body>
</html>