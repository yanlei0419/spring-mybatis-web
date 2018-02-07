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
$.extend($.fn.datagrid.methods,{
	excel:function(jq, param) {
			jq.datagrid('loading');
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
		 			jq.datagrid('loaded');
		 		}
		 	});

	}
});


$.extend($.fn.datagrid.methods, {
	autoMergeCells : function(jq, fields) {
		return jq.each(function() {
			var target = $(this);
			if (!fields) {
				fields = target.datagrid("getColumnFields");
			}
			var rows = target.datagrid("getRows");
			var i = 0,
			j = 0,
			temp = {};
			for (i; i < rows.length; i++) {
				var row = rows[i];
				j = 0;
				for (j; j < fields.length; j++) {
					var field = fields[j];
					var tf = temp[field];
					if (!tf) {
						tf = temp[field] = {};
						tf[row[field]] = [ i ];
					} else {
						var tfv = tf[row[field]];
						if (tfv) {
							tfv.push(i);
						} else {
							tfv = tf[row[field]] = [ i ];
						}
					}
				}
			}
			$.each(temp, function(field, colunm) {
				$.each(colunm, function() {
					var group = this;
					if (group.length > 1) {
						var before,
						after,
						megerIndex = group[0];
						for (var i = 0; i < group.length; i++) {
							before = group[i];
							after = group[i + 1];
							if (after && (after - before) == 1) {
								continue;
							}
							var rowspan = before - megerIndex + 1;
							if (rowspan > 1) {
								target.datagrid('mergeCells', {
									index : megerIndex,
									field : field,
									rowspan : rowspan
								});
							}
							if (after && (after - before) != 1) {
								megerIndex = after;
							}
						}
					}
				});
			});
		});
	}
});




$.extend($.fn.treegrid.methods,{
	excel:function(jq, param) {
			
			var options = jq.treegrid('options');//这样来获得所有的datagrid属性
			var total = jq.treegrid('getData').total;
			
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
		 	jq.treegrid('loading');
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
		 			jq.treegrid('loaded');
		 		}
		 	});

	}
});
