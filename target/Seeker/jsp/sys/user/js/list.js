function initTable() {
	$('#UserTable').datagrid({
		title : '用户管理列表',// 标题文字
		iconCls : 'icon-archive',// 一个css类，将提供一个背景图片作为标题图标
		width :width,// fillsize(0.99)
		method:'get',
		height:height,
		nowrap : false,// 是否在一行显示数据
		striped : true,// 奇偶行使用不同背景色，默认为false
		collapsible : false,
		url : basePath+'/user/getList.do',// 从远程站点请求数据的URL
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
			username : $("#username").val()
		},	
				columns:[[				
					{field:'id',title:'ID',width:fillsize(1),align:'center',hidden:true,sortable:false},
					{field:'username',title:'用户名',width:fillsize(1),align:'center',hidden:false,sortable:true},
					{field:'name',title:'姓名',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'tel',title:'电话',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'email',title:'邮箱',width:fillsize(1),align:'center',hidden:false,sortable:false},
					{field:'status',title:'状态',width:fillsize(0.5),align:'center',hidden:false,sortable:false,
						formatter:function(value,row,index){
							if(value=="1"){
								return '<input type="button" onclick="javascript:disableEnable(\''+row.id+'\',\'0\')" value="禁用" />';
							}else if(value="0"){
								return '<input type="button" onclick="javascript:disableEnable(\''+row.id+'\',\'1\')" value="启用" />';
							}
							return value;
						}
					},
					{field:'remark',title:'备注',width:fillsize(1),align:'left',hidden:false,sortable:false}
				]],
 				toolbar:[
 					{
 						text:'添加',
 						disabled:false,
 						iconCls:'icon-add',
 						handler:Add
 					}
 					,'-',{
						text:'修改',
						disabled:false,//加上disabled:true属性就能使该按钮变灰不可用 
						iconCls:'icon-edit',
						handler:Update
					},'-',{
						text:'删除',
						disabled:false,//加上disabled:true属性就能使该按钮变灰不可用 
						iconCls:'icon-remove',
						handler:Del
					},'-',{
						text:'重置密码',
						disabled:false,//加上disabled:true属性就能使该按钮变灰不可用 
						iconCls:'icon-undo',
						handler:UpdatePW
					}
 				],
		rowStyler:function(index,row){
		},
		onBeforeEdit : function(index, row) {
		},
		onAfterEdit : function(index, row) {
		},
		onCancelEdit : function(index, row) {
		},
		onClickRow : function(index, rowData) {// 先进单元格,然后进入行
		}
	});
	clearSelections();//取消已经选择的多选框
}

function UpdatePW(){
	var rows = $('#UserTable').datagrid('getSelections');
	if (rows.length <= 0) {
		alert("请选择一条记录");
		return;
	}
//	if (rows.length >1) {
//		alert("只能选择一条记录");
//		return;
//	}
	var ids=[];
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.ajax({ 
			type : "post", 
			url:basePath+'/user/updatePw.do', 
			traditional: true,//在struts2下该属性必须有 
			data:{ids:ids}, 
			dataType:'json',
			success:function(rows){
				if(rows>0){
					alert("密码重置完成");
				}else{
					alert("密码重置失败");
				}
			}
		});
}

function Add(){
	window.location.href=basePath+"/jsp/sys/user/add.jsp";
}
function Update(){
	var rows = $('#UserTable').datagrid('getSelections');
	if (rows.length <= 0) {
		alert("请选择一条记录");
		return;
	}
	if (rows.length >1) {
		alert("只可以选择一条数据");
		return;
	}
	window.location.href=basePath+"/user/handle.do?flag=update&id="+rows[0].id;
}
function Del(){
	var rows = $('#UserTable').datagrid('getSelections');
	if (rows.length <= 0) {
		alert("请选择一条记录");
		return;
	}
	var ids=[];
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.ajax({ 
			type : 'post', 
			async:true,//是否异步请求
			url:basePath+'/user/delete.do', 
			traditional: true,//在struts2下该属性必须有 
			data:{ids:ids}, 
			dataType:'json',
			success:function(rows){
				if(rows>0){
					alert("删除成功");
					initTable();
				}else{
					alert("删除失败");
				}
			}
	});
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
function disableEnable(id,status){
	$.ajax({ 
		type : 'post', 
		async:true,//是否异步请求
		url:basePath+'/user/disableEnable.do', 
		traditional: true,//在struts2下该属性必须有 
		data:{id:id,status:status}, 
		dataType:'json',
		success:function(rows){
			if(rows>0){
				alert("修改成功");
				initTable();
			}else{
				alert("修改失败");
			}
		}
	});
}