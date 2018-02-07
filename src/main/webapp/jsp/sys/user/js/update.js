function senfe(o, a, b, c, d) {
	var t = document.getElementById(o).getElementsByTagName("tr");
	for (var i = 0; i < t.length; i++) {
		t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
	}
}
$(document).ready(function() {
	senfe("User", "#f3f8fd", "#ffffff", "#ADADAD", "#f391a9");
	$.formValidator.initConfig({
		formID : "update",
		theme : 'SolidBox',
		mode : 'AutoTip',
		inIframe : true
	});
	$("#tel").formValidator({
		empty : true,
		onShow : "请输入电话",
		onFocus : "请输入电话",
		onCorrect : "您输入的电话合法"
	}).inputValidator({
		//	min:1,
		max : 20,
		onError : "电话有误,请确认"
	}).regexValidator({
		regExp : [ "tel", "mobile" ],
		dataType : "enum",
		onError : "你输入的手机或电话格式不正确"
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
