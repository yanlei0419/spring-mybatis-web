<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/include/doctype.jsp" flush="true" /><html>
<html>
<head>
    <title>操作日志查询</title>
    <link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/css/page.css"  />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
	<jsp:include page="/jsp/include/easyui.jsp" flush="true"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/sys/syslog/js/log_list.js?ve=3" ></script>
    <script type="text/javascript"></script>
    <script type="text/javascript">
  		var basePath="${pageContext.request.contextPath}";
  		function ve(mark_id){
  			window.location.href=basePath+'/jsp/analysis/list.jsp?mark_id='+mark_id;
  		}
  		function Clear(){
  			$("#startTime").val("");
  			$("#endTime").val("");
  		}
		</script>
    <style type="text/css">
    body{margin: 0px; padding: 0px;}
    *{font-family:"微软雅黑";}
.base{
border: 1px solid ; 
}

    </style>
</head>
<body>

<input type="hidden"  value="${param.mark_id}" id="mark_id"/>
    <div id="ve">
      <div id="condition" style="">
           <div id="MyPanel" class="easyui-panel" style="width:'100%';padding:6px;" data-options="title:'操作日志查询',iconCls:'icon-search',collapsible:false,minimizable:false,maximizable:false,closable:false">
              开始时间 : <input type="text" id="startTime"  class="Wdate" ondblclick=""  onClick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endTime\');}'})"/>&nbsp;&nbsp;
              结束时间 : <input type="text" id="endTime" class="Wdate"onClick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startTime\');}'})"/>
				<a href="javascript:void(0)" onclick="initTable()" class="easyui-linkbutton" plain="true" icon="icon-search" >查询</a>
				<a href="javascript:void(0)" onclick="Clear()" class="easyui-linkbutton" plain="true" icon="icon-clear">清除</a>
			</div>
		</div>
    	<div id="ve">
            <div id="myTable">
           		<table id="sysLogTable"/>
            </div>
        </div>
    </div>
</body>
</html>
<script type="text/javascript"></script> 