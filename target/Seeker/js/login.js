function login() {
	var u = $('#username').val();
	var p = $('#password').val();
	if (u.length <= 0) {
		alert("帐户不可以为空");
		return;
	}
	if (p.length <= 0) {
		alert("密码不可以为空");
		return;
	}
	$.ajax({
		type : "get",
		url : basePath + '/login/handle.do',
		traditional : true,//在struts2下该属性必须有 
		data : $('#myform').serialize(),
		dataType : 'text',
		success : function(result) {
			if (result == "SUCCESS") {
				window.location.href = basePath + "/demo/index_1.jsp"
				//  					alert("登录成功")
			} else if (result == "NULL") {
				alert("帐户不存在")
			} else if (result == "ERROR") {
				alert("帐户被冻结")
			} else if (result == "PASSWORD") {
				alert("帐户密码错误")
			} else {
				alert("其他错误");
			}
		},
		error : function(e) {
			alert("请求失败!!!!");
		}
	});
}