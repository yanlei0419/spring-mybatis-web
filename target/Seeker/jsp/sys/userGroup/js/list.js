function initTable() {
	$('#UserTable').datagrid({
		title : '角色用户列表',// 标题文字
		iconCls : 'icon-archive',// 一个css类，将提供一个背景图片作为标题图标
		width :width,// fillsize(0.99)
		method:'POST',
		height:height,
		nowrap : false,// 是否在一行显示数据
		striped : true,// 奇偶行使用不同背景色，默认为false
		collapsible : false,
		url : basePath+'/user/group/getList.do',// 从远程站点请求数据的URL
		// sortName: 'id',//可以排序的列
		// sortOrder: 'desc',//定义列的排序顺序，只能用 'asc' 或 'desc'
		remoteSort : true,// 定义是否从服务器给数据排序
		singleSelect : false,// 只允许选中一行
		selectOnCheck:true,//选中checkbox时同时选中改行
		checkOnSelect:true ,//选中一行时同时选中checkbox
		fitColumns : true,// 自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动
		pageNumber : 1,// 当设置了 pagination 特性时，初始化页码
//		pageSize : pageSize,// 当设置了 pagination 特性时，初始化页码尺寸，即每页显示的记录条数，默认为10
		pageList :pageList,// 当设置了pagination
		idField : 'id',// 标识字段
		frozenColumns : [ [
			{field:'ck',checkbox:true}
		] ],
		queryParams : {
			name : $("#name").val(),
			username : $("#username").val(),
			groupId:$("#groupId").val(),
			status:1
		},	
				columns:[[				
					{field:'id',title:'ID',width:fillsize(1),align:'center',hidden:true,sortable:false},
					{field:'username',title:'用户名',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'name',title:'姓名',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'tel',title:'电话',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'email',title:'邮箱',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'status',title:'状态',width:fillsize(1),align:'center',hidden:true,sortable:false,
						formatter:function(value,row,index){
							if(value=="1"){
								return "是";
							}else if(value="0"){
								return "否"
							}
							return value;
						}
					},
					{field:'remark',title:'备注',width:fillsize(1),align:'left',hidden:false,sortable:false}
				]],
 				toolbar:[
 					{
 						text:'选择',
 						disabled:false,
 						iconCls:'icon-add',
 						handler:Add
 					}
 					,'-',{
						text:'删除',
						disabled:false,//加上disabled:true属性就能使该按钮变灰不可用 
						iconCls:'icon-remove',
						handler:Del
					}
 					,'-',{
						text:'返回',
						disabled:false,//加上disabled:true属性就能使该按钮变灰不可用 
						iconCls:'icon-undo',
						handler:back
					}
 				]
	});
	clearSelections();//取消已经选择的多选框
}


function Add(){
	var url=basePath+"/jsp/sys/sel/sel_user_list.jsp?groupId="+$('#groupId').val();
	openWin(url);
}
function back(){
	window.location.href=basePath+"/jsp/sys/group/list.jsp";
}


function Del() {
	var rows = $('#UserTable').datagrid('getSelections');
	if (rows.length <= 0) {
		alert("请选择一条记录");
		return;
	}
	var ids = [];
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.ajax({
		type : 'post',
		async : true,// 是否异步请求
		url : basePath + '/user/group/delete.do',
		traditional : true,// 在struts2下该属性必须有
		data : {
			ids : ids,
			groupId : $("#groupId").val()
		},
		dataType : 'json',
		success : function(rows) {
			alert("删除成功");
			initTable();
		}
	});
}


function addUserGroup(rows) {
	var ids = [];
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.ajax({
		type : 'post',
		async : true,// 是否异步请求
		url : basePath + '/user/group/add.do',
		traditional : true,// 在struts2下该属性必须有
		data : {
			ids : ids,
			groupId : $("#groupId").val()
		},
		dataType : 'json',
		success : function(rows) {
//			alert("添加成功");
			initTable(); 
		}
	});
//	console.log(rows);
}

$(function() {
	initTable();
});
$(window).resize(function() {
	$('#UserTable').datagrid('resize');
});
function clearSelections() {
	$('#UserTable').datagrid('clearSelections');
}
