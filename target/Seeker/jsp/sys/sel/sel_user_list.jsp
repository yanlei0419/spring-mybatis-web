<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/include/doctype.jsp" flush="true" />
<html>
<head>
    <title>选择列表</title>
    
    <link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/css/page.css"  />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
    <jsp:include page="/jsp/include/easyui.jsp" flush="true"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/sys/sel/js/sel_user_list.js?ve=3" ></script>
    <script type="text/javascript">
  		var basePath="${pageContext.request.contextPath}";
  		function Clear(){
  			$("#name").val("");
  			$("#username").val("");
  		}
//   		window.opener.addCabinet(rows);
// 		window.close();
		</script>
    <style type="text/css">
    body{margin: 0px; padding: 0px;}
    *{font-family:"微软雅黑";}
.base{
/* border: 1px solid ; */
}
    </style>
</head>
<body>   
	<input type="hidden" id="flag" name="flag" value="group"/>
   <input type="hidden" id="groupId" name="groupId" value="${param.groupId }"/>
    <div style="width: 790px; margin-left: auto;margin-right: auto;">
    	 <div id="condition" style="width: 790px; margin-left: auto;margin-right: auto;">
           <div id="MyPanel" class="easyui-panel" style="width:'100%';padding:6px;" data-options="title:'选择用户查询',iconCls:'icon-search',collapsible:false,minimizable:false,maximizable:false,closable:false">
              姓名 : <input type="text" id="name" name="name" />&nbsp;&nbsp;
              用户名 : <input type="text" id="username" name="username"  />
				<a href="javascript:void(0)" onclick="initTable()" class="easyui-linkbutton" plain="true" icon="icon-search" >查询</a>
				<a href="javascript:void(0)" onclick="Clear()" class="easyui-linkbutton" plain="true" icon="icon-clear">清除</a>
			</div>
		</div>
            <div id="myTable " style="width: 790px;  margin-left: auto;margin-right: auto;">
            	<table id="UserTable"></table>
             </div>
        </div>

        
    </div>
</body>
</html>
<script type="text/javascript"></script> 