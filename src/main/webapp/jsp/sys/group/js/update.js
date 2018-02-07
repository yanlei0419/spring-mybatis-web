function senfe(o, a, b, c, d) {
	var t = document.getElementById(o).getElementsByTagName("tr");
	for (var i = 0; i < t.length; i++) {
		t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
	}
}
$(document).ready(function() {
	senfe("Group", "#f3f8fd", "#ffffff", "#ADADAD", "#f391a9");
	$.formValidator.initConfig({
		formID : "update",
		theme : 'SolidBox',
		mode : 'AutoTip',
		inIframe : true
	});
	$("#groupName").formValidator({
		empty : false,
		onShow : "请输入角色名",
		onFocus : "请输入角色名",
		onCorrect : "您输入的角色名合法"
	}).inputValidator({
		min : 1,
		max : 40,
		onError : "角色名有误,请确认"
	}).ajaxValidator({
		type : "get",
		url : basePath + "/group/checkGroupName.do",
		dataType : "text",
		data : {
			flag : 'check',
			id : $("#id").val()
		},
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

	$("#remark").formValidator({
		empty : true,
		onShow : "请输入备注",
		onFocus : "请输入备注",
		onCorrect : "您输入的备注合法"
	}).inputValidator({
		//min:1,
		max : 4000,
		onError : "备注有误,请确认"
	});

});
$(window).resize(function() {
	$.formValidator.reloadAutoTip();
	$('#GroupPanel').panel('resize');
});
