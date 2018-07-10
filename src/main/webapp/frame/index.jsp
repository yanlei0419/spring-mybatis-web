<%@page import="org.seeker.common.util.SysConstant"%>
<%@ page language="java" import="java.util.List" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="${pageContext.request.contextPath}/frame/"/> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=SysConstant.SYSTEM_NAME %></title>
        <title></title>
		<!--框架必需start-->
		<link href="${pageContext.request.contextPath}/frame/css/import_basic.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/frame/skins/dams/import_skin.css" rel="stylesheet" type="text/css" id="skin" themeColor="blue" prePath="./" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/frame/js/bsFormat.js"></script>
		<!--框架必需end-->
        
		<!--弹出式提示框start-->
		<script type="text/javascript" >
            
            //退出系统 确认消息
            function LogOut(){
            	if(window.confirm('您确定要退出本系统吗？')){
//             		window.location.href="${pageContext.request.contextPath}/logout"
            		window.location.href="${pageContext.request.contextPath}/login/loginout.do"
                    return true;
                 }else{
                    return false;
                }
            }
            
            function loadFrame(obj){
                var url = obj.contentWindow.location.href;  
                if(url.indexOf("login.jsp")!=-1){  
                    window.location.href = "${pageContext.request.contextPath}/login.jsp";  
                }  
            }
		</script>
		<!--弹出式提示框end-->

	</head>
	<body>
		<input type="hidden" id="currMenuId" value="" />
		<div id="floatPanel-1"></div>
		<div id="mainFrame">
			<!--头部与导航start-->
			<div id="hbox">
				<div id="bs_bannercenter" style="height: 40px; background-image: none;border-bottom: 2px solid #95b8e7;">
					<div id="bs_bannerleft">
						<div id="bs_bannerright2">
							<div class="bs_banner_logo_hmenu" ></div>
							<div class="bs_banner_title" style="display: none;"></div>
							<div class="nav_icon_h">
								<div class="clear"></div>
							</div>
							<div class="bs_nav" style="padding: 10px 0px;">
								<div class="bs_navleft" id="divs1">
									<li>
										<img src="${pageContext.request.contextPath}/frame/skins/dams/mainframe/ico_02.png" />
										<strong></strong>  &nbsp;&nbsp;&nbsp;&nbsp;您好[ ${User.name } ]，今天是
										<script>
											var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
											var now = new Date();
										    var year=now.getFullYear();
											var month=now.getMonth()+1;
											var day=now.getDate()
										    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
											document.write(currentime)
										</script>
									</li>
									<div class="clear"></div>
								</div>
								<div class="bs_navright">
									<span class="icon_exit hand" onclick='javascript:LogOut()'>退出系统</span>
									<div class="clear"></div>
								</div>
								<div class="clear"></div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<!--头部与导航end-->

			<table width="100%" cellpadding="0" cellspacing="0" class="table_border0" >
				<tr>
					<!--左侧区域start-->
					<td id="hideCon" class="ver01 ali01">
						<script>
							$("#hideCon").attr("height",($(document).height()-$("#hbox").height())+"px;");
						</script>
						<div id="lbox">
							<div id="lbox_topcenter">
								<div id="lbox_topleft">
									<div id="lbox_topright">
										<div class="lbox_title">
											操作菜单
										</div>
									</div>
								</div>
							</div>
							<div id="lbox_middlecenter">
								<div id="lbox_middleleft">
									<div id="lbox_middleright">
										<div id="bs_left" style="width:150px;">
											<IFRAME scrolling="auto" onload="loadFrame(this)" width="100%" frameBorder=0 id="frmleft" name="frmleft" src="${pageContext.request.contextPath}/frame/leftPages/left.jsp" allowTransparency="true"></IFRAME>
										</div>
										<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
									</div>
								</div>
							</div>
							<div id="lbox_bottomcenter">
								<div id="lbox_bottomleft">
									<div id="lbox_bottomright">
										<div class="lbox_foot"></div>
									</div>
								</div>
							</div>
						</div>
					</td>
					<!--左侧区域end-->

					<!--中间栏区域start-->
					<td id="hideLeft" class="main_shutiao">
						<div class="bs_leftArr" id="bs_center" title="收缩面板"></div>
					</td>
					<!--中间栏区域end-->

					<!--右侧区域start-->
					<td class="ali01 ver01" width="100%">
						<div id="rbox">
							<div id="rbox_topcenter">
								<div id="rbox_topleft">
									<div id="rbox_topright">
										<div class="rbox_title">
											操作内容
										</div>
									</div>
								</div>
							</div>
							<div id="rbox_middlecenter">
								<script>
									$("#rbox_middlecenter").attr("height",($(document).height())+"px;");
								</script>
								<div id="rbox_middleleft">
									<div id="rbox_middleright">
										<div id="bs_right">
											<IFRAME scrolling="auto" onload="loadFrame(this)" height="100%" width="100%" frameBorder=0 id="frmright" name="frmright" src="${pageContext.request.contextPath}/frame/right.jsp" allowTransparency="true"></IFRAME>
										</div>
									</div>
								</div>
							</div>             
							<div id="rbox_bottomcenter">
								<div id="rbox_bottomleft">
									<div id="rbox_bottomright">

									</div>
								</div>
							</div>
						</div>
					</td>
					<!--右侧区域end-->
				</tr>
			</table>

			<!--尾部区域start-->
			<div id="fbox" style="display:none;height:0px;">
				<div id="bs_footcenter">
					<div id="bs_footleft">
						<div id="bs_footright" class="white">
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--尾部区域end-->

		<!--浏览器resize事件修正start-->
		<div id="resizeFix"></div>
		<!--浏览器resize事件修正end-->
	</body>
</html>
