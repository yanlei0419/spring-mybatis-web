<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<jsp:include page="/jsp/include/doctype.jsp" flush="true" />
<html>
<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/plugins/bootstrap3.3.7/css/bootstrap.min.css"  />
	<jsp:include page="/jsp/include/jquery.jsp"  flush = "true" />
	<style type="text/css">
	.b{border: 1px solid black;}
	</style>
	<script type="text/javascript">
	var basePath="${pageContext.request.contextPath}";
	function handle(){
		var op = $('#oldPassword').val();
		var p = $('#password').val();
		var np = $('#newPassword').val();
		if (op.length <= 0) {
			alert("旧密码不可以为空!!!");
			return;
		}
		if (p.length <= 0) {
			alert("新密码不可以为空!!!!");
			return;
		}
		if (p!=np) {
			alert("两次密码不一致!!!!");
			return;
		}
		$.ajax({
			type : "POST",
			url : basePath + '/user/updateNewPW.do',
			traditional : true,//在struts2下该属性必须有 
			data : $('#myform').serialize(),
			dataType : 'text',
			success : function(result) {
				if(result=="SUCCESS"){
					top.window.location.href=basePath+"/logout";
// 					top.window.location.href=basePath+"/login/loginout.do";
				}else if (result == "ERROR") {
					alert("旧密码验证失败")
				} else {
					alert("其他错误");
				}
			},
			error : function(e) {
				alert("请求失败!!!!");
			}
		});
	}
	</script>

</head>
<body class="bg-warning">
<div class="container" style="width: 540px; height: 300px;margin: 10px auto; ">
   <div class="jumbotron " style="padding-left: 100px; padding-right: 100px;">
   		<h2 style="text-align: center; color: black;" >修改密码</h2> 
	    <form class="bs-example bs-example-form" role="form" method="post" id="myform">
	        <div class="input-group input-group-lg  ">
	            <span class="input-group-addon">&nbsp;旧&nbsp;密&nbsp;码</span>
	            <input type="password" class="form-control" placeholder="旧密码" id="oldPassword" name="oldPassword"/>
	        </div>
	        <br/>
	        <div class="input-group  input-group-lg ">
	            <span class="input-group-addon">&nbsp;新&nbsp;密&nbsp;码</span>
	            <input type="password" class="form-control"  placeholder="新密码" id="password" name="password"/>
	        </div>
	        <br/>
	        <div class="input-group  input-group-lg ">
	            <span class="input-group-addon">确认密码</span>
	            <input type="password" class="form-control"  placeholder="确认密码" id="newPassword" name="newPassword"/>
	        </div>
	        <div class="input-group  well-lg text-center">
	        	<a class="btn btn-primary btn-lg " role="button" style="width: 260px; " onclick="javascript:handle()">修改密码</a>
	        </div>
	    </form>
	</div>
</div>
</body>
</html>