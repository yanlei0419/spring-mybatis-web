<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/include/doctype.jsp" flush="true" />
<html>
<head>
<base href="">

<title>菜单</title>

<jsp:include page="/jsp/include/jquery.jsp" flush="true" />
<jsp:include page="/jsp/include/easyui.jsp" flush="true" />
<jsp:include page="/jsp/include/ztree.jsp" flush="true" />

<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/sys/menu/js/index.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>

    <div class="easyui-layout" fit="true">
	    <div id="groupPanel" data-options="region:'west',split:true" title="选择授权角色" style="width: 300px;">
			<ul id="GroupTree" class="ztree"></ul>
		</div>
		
		
		<div id="MenuPanel"  data-options="region:'center'" title="权限设置 " >
			  <a href="javascript:void(0)"  onclick="javascript:expandAllMenuTree(true)"  style="cursor: pointer;disabled:'';" class="easyui-linkbutton" onmouseover="" plain="true" icon="icon-add" >展开</a>
			  <a href="javascript:void(0)"  onclick="javascript:expandAllMenuTree(false)" style="cursor: pointer;" class="easyui-linkbutton" onmouseover="" plain="true" icon="icon-remove" >收起</a>
			  <a href="javascript:void(0)"  onclick="javascript:saveMenuSet()" style="cursor: pointer;" class="easyui-linkbutton" onmouseover="" plain="true" icon="icon-save" >保存</a>
			<HR>
			<ul id="MenuTree" class="ztree"></ul>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var basePath = "${pageContext.request.contextPath}/";
	$(function() {
		initGroupTree();
// 		initMenuTree()
	});
</script>