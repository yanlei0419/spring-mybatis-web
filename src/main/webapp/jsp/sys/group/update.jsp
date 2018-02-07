<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String pageUrl = request.getRequestURL().toString();
	String listpageUrl = pageUrl.substring(0,pageUrl.lastIndexOf("/")+1)+"list.jsp";
	
%>
<jsp:include page="/jsp/include/doctype.jsp" flush="true"/>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>编辑角色</title>
		<jsp:include page="/jsp/include/jquery.jsp" flush="true"/>
		<jsp:include page="/jsp/include/easyui.jsp" flush="true"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/formValidator4.1.3/formValidator-4.1.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/formValidator4.1.3/formValidatorRegex.js"></script>
		<script type="text/javascript" src="<%= basePath %>/jsp/sys/group/js/update.js"></script>
		
		<style  type="text/css">
		</style>
		
		<script language="javascript">
		var basePath="${pageContext.request.contextPath}";
		</script>
		
	</head>
	<body>
		<div id="GroupPanel" class="easyui-panel" style="width:'100%';padding:6px;" data-options="title:'编辑角色',iconCls:'icon-add',collapsible:true,minimizable:false,maximizable:false,closable:false">
		<form id="update" name="update" action="<%=basePath%>/group/update.do" method="post">
			<input type="hidden" name="id" id="id" value="${GroupPo.id}"/>
			<table id="Group" cellspacing="1" cellpadding="0" border="0" bgcolor="#95a5d2" width="100%">
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						角色名
					</td>
					<td align="left" width="70%">
						<input type="text" id="groupName" name="groupName" value="${GroupPo.groupName}"/>
					</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						备注
					</td>
					<td align="left" width="70%">
						<input type="text" id="remark" name="remark" value="${GroupPo.remark}"/>
					</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td  colspan="2" align="center" width="100%">
						<a href="javascript:void(0)" onclick="$('#update').submit()" class="easyui-linkbutton" id="prsBtn" plain="true" icon="icon-save">提交</a>
						<a href="javascript:void(0)" onclick="$('#update')[0].reset()" class="easyui-linkbutton" plain="true" icon="icon-reload">重置</a>
						<a href="javascript:void(0)" onclick="javascript:window.location.href='<%=listpageUrl %>';" class="easyui-linkbutton" plain="true" icon="icon-back">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
	
