<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String pageUrl = request.getRequestURL().toString();
	String listpageUrl = pageUrl.substring(0,pageUrl.lastIndexOf("/")+1)+"list.jsp";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>查看用户</title>
		<jsp:include page="/jsp/include/jquery.jsp" flush="true"/>
		<jsp:include page="/jsp/include/easyui.jsp" flush="true"/>
		
		<style  type="text/css">
		</style>
		
	</head>
	<body>
		<div id="UserPanel" class="easyui-panel" style="width:'100%';padding:6px;" data-options="title:'查看用户',iconCls:'icon-add',collapsible:true,minimizable:false,maximizable:false,closable:false">
			<table id="User" cellspacing="1" cellpadding="0" border="0"  width="100%">
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						用户名
					</td>
					<td  align="left" width="70%">${UserPo.username}</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						姓名
					</td>
					<td  align="left" width="70%">${UserPo.name}</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						电话
					</td>
					<td  align="left" width="70%">${UserPo.tel}</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						邮箱
					</td>
					<td  align="left" width="70%">${UserPo.email}</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						创建时间
					</td>
					<td  align="left" width="70%">${UserPo.create_time}</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						创建人
					</td>
					<td  align="left" width="70%">${UserPo.createBy}</td>
				</tr>
<!-- 				<tr height="30" align="left" valign="middle"> -->
<!-- 					<td  align="center" width="30%"> -->
<!-- 						启用状态 -->
<!-- 					</td> -->
<%-- 					<td  align="left" width="70%">${UserPo.status}</td> --%>
<!-- 				</tr> -->
				<tr height="30" align="left" valign="middle">
					<td  align="center" width="30%">
						备注
					</td>
					<td  align="left" width="70%">${UserPo.remark}</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td colspan="2" align="center" width="100%">
						<a href="javascript:void(0)" onclick="javascript:window.location.href='<%=listpageUrl %>';" class="easyui-linkbutton" plain="true" icon="icon-back">返回</a>
					</td>
				</tr>
			</table>
	</body>
</html>
	
