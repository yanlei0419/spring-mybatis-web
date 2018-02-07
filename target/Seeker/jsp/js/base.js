function isNotNull(val){
	return val!=null&&val!=''&&val!='null'&&val!=undefined&&val!='undefined'&&val.length>0?true :false;
}

function isNull(data){ 
	return !isNotNull(data); 
}

//function isNull(data){ 
//	return (data == null||data == undefined ||data == "" ||data!='null'||data!='undefined'||data.length==0) ? true:false; 
//}
//
//function isNotNull(val){
//	return !isNull(val)
//}

function selVal(id,val){
	$('#'+id).val(val);
}

function setVal(id,val){
	$('#'+id).val(val);
}

function setVals(ids,vals){
	if((ids instanceof Array)&&(vals instanceof Array)){
		if(ids.length===vals.length){
			for (var i = 0; i < ids.length; i++) {
				setVal(ids[i],vals[i]);
			}
		}
	}
}

function clearVals(ids){
	if(ids instanceof Array){
		for (var i = 0; i < ids.length; i++) {
			setVal(ids[i],"");
		}
	}
}

