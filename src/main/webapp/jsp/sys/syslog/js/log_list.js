function initTable() {
	$('#sysLogTable').datagrid({
		title : '操作日志列表',// 标题文字
		iconCls : 'icon-archive',// 一个css类，将提供一个背景图片作为标题图标
		width : width,// fillsize(0.99)
		height : height,
		nowrap : false,// 是否在一行显示数据
		striped : true,// 奇偶行使用不同背景色，默认为false
		collapsible : false,
		url : basePath+'/syslog/getList.do',// 从远程站点请求数据的URL
		remoteSort : true,// 定义是否从服务器给数据排序
		singleSelect : true,// 只允许选中一行
//		checkOnSelect:false,//选中一行时同时选中checkbox
//		 selectOnCheck:true,//选中checkbox时同时选中改行
		fitColumns : true,// 自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动
		pageNumber : 1,// 当设置了 pagination 特性时，初始化页码
//		pageSize : pageSize,// 当设置了 pagination 特性时，初始化页码尺寸，即每页显示的记录条数，默认为10
		pageList :pageList,// 当设置了pagination
		idField : 'id',// 标识字段
		sortName : "operate_Time",
		sortOrder : "desc",
//		frozenColumns : [ [
//			{field:'ck',checkbox:true}
//		] ],
		queryParams : {
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val()
		},	
		columns:[[	
					{field:'id',title:'主键',width:fillsize(1),align:'center',hidden:true,sortable:false},
					{field:'operateModule',title:'模块名',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'operateType',title:'操作类型',width:fillsize(1),align:'center',hidden:false,sortable:false,
						formatter:function(value,row,index){
							if(value=="1"){
								return "添加";
							}else if(value=="2"){
								return "删除";
							}else if(value=="3"){
								return "修改";
							}else if(value=="4"){
								return "查询";
							}else if(value=="5"){
								return "导出";
							}else if(value=="6"){
								return "批量添加";
							}
							return value;
						}
					},
					{field:'operateTime',title:'操作时间',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'operateBy',title:'操作人',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'operateRemark',title:'操作内容',width:fillsize(2),align:'left',hidden:false,sortable:false},
					{field:'operateContent',title:'输入条件',width:fillsize(2),align:'left',hidden:true,sortable:false},
					{field:'operateIp',title:'',width:fillsize(1),align:'center',hidden:true,sortable:false	}
		]]
	});
}
$(function() {
	initTable();
});
function  initTableS(){
	var options = $('#analysisTable').datagrid("getPager").data("pagination").options;
	pageSize= options.pageSize;//每页的记录数（行数
	initTable();
}
$(window).resize(function() {
	$('#MyPanel').panel('resize');
	$('#sysLogTable').datagrid('resize');
});