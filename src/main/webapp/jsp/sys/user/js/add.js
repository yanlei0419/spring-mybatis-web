function senfe(o, a, b, c, d) {
	var t = document.getElementById(o).getElementsByTagName("tr");
	for (var i = 0; i < t.length; i++) {
		t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
	}
}
$(document).ready(function() {
	senfe("User", "#f3f8fd", "#ffffff", "#ADADAD", "#f391a9");
	$.formValidator.initConfig({
		formID : "add",
		theme : 'SolidBox',
		mode : 'AutoTip',
		inIframe : true
	});
	$("#username").formValidator({
		empty : false,
		onShow : "请输入帐户名",
		onFocus : "请输入帐户名",
		onCorrect : "您输入的帐户名合法"
	}).inputValidator({
		min : 1,
		max : 25,
		onError : "帐户名有误,请确认"
	}).regexValidator({
		regExp : [ "username" ],
		dataType : "enum",
		onError : "此用户名字符有误,请检查"
	}).ajaxValidator({
		type : "POST",
		url : basePath + "/user/checkUsername.do",
		data : {
			username : $("#username").val()
		},
		dataType : "json",
		async : true,
		success : function(data) {
			if (data == 0) {
				return true;
			} else {
				return false;
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
		},
		onError : "此用户名已被注册，请更换用户名",
		onWait : "正在对用户名进行合法性校验，请稍候..."
	});
	
	$("#name").formValidator({
		empty : false,
		onShow : "请输入姓名",
		onFocus : "请输入姓名",
		onCorrect : "您输入的姓名合法"
	}).inputValidator({
		min : 1,
		max : 40,
		onError : "姓名有误,请确认"
	});

	$("#tel").formValidator({
		empty : true,
		onShow : "请输入电话",
		onFocus : "请输入电话",
		onCorrect : "您输入的电话合法"
	}).regexValidator({
		regExp : [ "tel", "mobile" ],
		dataType : "enum",
		onError : "你输入的手机或电话格式不正确"
	}).inputValidator({
		//	min:1,
		max : 20,
		onError : "电话有误,请确认"
	});

	$("#email").formValidator({
		empty : true,
		onShow : "请输入邮箱",
		onFocus : "请输入邮箱",
		onCorrect : "您输入的邮箱合法"
	}).inputValidator({
		//	min:1,
		max : 20,
		onError : "邮箱有误,请确认"
	}).regexValidator({
		regExp : [ "email" ],
		dataType : "enum",
		onError : "Email格式不正确，请检查"
	});
//	.regexValidator({
//		regExp:["intege"]//intege1正整数 intege2负整数 
//	});

	$("#remark").formValidator({
		empty : true,
		onShow : "请输入备注",
		onFocus : "请输入备注",
		onCorrect : "您输入的备注合法"
	}).inputValidator({
		//	min:1,
		max : 4000,
		onError : "备注有误,请确认"
	});

});
$(window).resize(function() {
	$.formValidator.reloadAutoTip();
	$('#UserPanel').panel('resize');
});
