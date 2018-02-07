$.extend($.fn.datagrid.methods, {
	editCell : function(jq, param) {
		return jq.each(function() {
			var opts = $(this).datagrid('options');//全部数据
			var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));//行数据
			for (var i = 0; i < fields.length; i++) {
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field) {
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for (var i = 0; i < fields.length; i++) {
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

//
//$("#dg").datagrid('getChanges'); //获取所有变更数据
//$("#dg").datagrid('getChanges', 'inserted'); //获取插入的数据
//$("#dg").datagrid('getChanges', 'deleted'); //获取删除的数据
//$("#dg").datagrid('getChanges', 'updated'); //获取修改的数据
// 
//$("#dg").datagrid('acceptChanges');//接受所有修改，即，getChanges获取不到数据了
//$("#dg").datagrid('rejectChanges');//退回所有修改
//
//$("#dg").datagrid('getChanges'); //获取所有变更数据
//$("#dg").datagrid('getChanges', 'inserted'); //获取插入的数据
//$("#dg").datagrid('getChanges', 'deleted'); //获取删除的数据
//$("#dg").datagrid('getChanges', 'updated'); //获取修改的数据
// 
//$("#dg").datagrid('acceptChanges');//接受所有修改，即，getChanges获取不到数据了
//$("#dg").datagrid('rejectChanges');//退回所有修改
$.extend($.fn.datagrid.methods,{
	excel:function(jq, param) {
			var options = jq.datagrid('options');//这样来获得所有的datagrid属性
			var total = jq.datagrid('getData').total;
			if(!total || total<1) {//总记录数为0，就不需要导出了
				alert('没有可下载的数据');
				return false;
			}else if(total>30000){
				if(!window.confirm('数据量较大,装载Excel文件需要较长时间,确定要执行吗?')) {
					return false;
				}
			}
			
			var sheetName;
			var fields = [];//对象字段名称
		 	var titles = [];//中文名称
		 	
			if(param) {
				if(param.sheetName) {
					sheetName = param.sheetName;
				}else{
					sheetName="sheet1"
				}
				if(param.fields) 
					fields = param.fields;
				if(param.titles) 
					titles = param.titles;
			}
			
			//2 处理url
		 	var url = options.url;
		 	if(url.indexOf('?')>0) {
			 	url += '&excel=true';
		 	}else  {
		 		url += '?excel=true';
		 	}
		 	
		 	//3 处理查询条件（这里用url带参数的方式传递到后台）
		 	var dataParams=options.queryParams;//不对本身参数进行修改,以免出现再次查询错误
		 	
		 	//4 处理要显示的field，包括字段名和对应显示的表头中文名
		 
		 	
		 	if(fields!=undefined && titles!=undefined) {//如果指定了，就用指定的field信息
		 		var all_columns = options.columns[0];//目前还不能处理复杂表头，如果是复杂表头，请自己将要下载的field和对应的显示名称封装成数组
		 		fields.push("");
		 		titles.push("序号");
			 	for(var att in all_columns) {
			 		if(all_columns[att] && !all_columns[att].hidden && all_columns[att].field) {
			 			fields.push(all_columns[att].field);
			 			titles.push(all_columns[att].title);
			 		}
			 	}
		 	}
		 	dataParams.excel_fields=fields.join(',');
		 	dataParams.excel_titles=titles.join(',');
		 	dataParams.sheetName=sheetName;
//		 	alert(dataParams);
			//5 提交ajax请求来生成excel文件并下载
		 	$.ajax({
		 		type:'post',
		 		async:false,
		 		url:url,
		 		dataType:"text",
		 		error:function (a,msg){
		 		},
		 		data:dataParams,
		 		success:function(path) {
//		 			alert(path);
		 			excelHandle(path);
		 		}
		 	});

	}
});




function endEditing(obj,row,field) {
	if (editIndex == undefined) {
		if(row.is_update=="0"&&field=="remark"){
			return false;
		}
		return true
	}
	if (obj.datagrid('validateRow', editIndex)) {
		obj.datagrid('endEdit', editIndex);
		editIndex = undefined;
		if(row.is_update=="0"&&field=="remark"){
				return false;
		}
		return true;
	} else {
		return false;
	}
}








