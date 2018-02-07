<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String userId="admin";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="${pageContext.request.contextPath}"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<jsp:include page = "/jsp/include/jquery.jsp" flush = "true"/>
		<jsp:include page = "/jsp/include/easyui.jsp" flush = "true"/>
        <link href="${pageContext.request.contextPath}/frame/skins/sky/import_skin.css" rel="stylesheet" type="text/css" id="skin" themeColor="blue" prePath="./" />
		<style type="text/css">
			body {margin: 0px;padding: 0px;} 
			.panel-body-noborder {border-width: 0;scrolling:"no"}
		</style>
		<script type="text/javascript" >
            
            function loadFrame(obj){
                var url = obj.contentWindow.location.href;  
                if(url.indexOf("login.jsp")!=-1){  
                    top.window.location.href = "${pageContext.request.contextPath}/login.jsp";  
                }  
            }
		</script>
	</head>

	<body onresize="WindowResize()" style="overflow:scroll;overflow-x:hidden;overflow-y:hidden;">
		<div id="tabs" class="easyui-tabs" style="height:560px;boder:0px;">
			<div title="我的办公桌" closable="false" style="overflow: hidden;background-color: #e8f6ff">
				<iframe id="mydesk" name="mydesk" scrolling="auto" frameborder="0" src="${pageContext.request.contextPath}/desktop/my_desktop.jsp" style="width: 100%; height: 100%"></iframe>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
        var count=0;
	    window.onerror=function(){  
          return true;
        }
	    var tabPanel="tabs";
	    var lastTabs = new Array();
	    
    	var left_page_height = $('#hideCon', parent.document).attr("height")
    	if(left_page_height.indexOf("px") > 0){
    		left_page_height = left_page_height.substring(0, left_page_height.indexOf("px"));
    	}
    	
// 	    $("#tabs").tabs({ 
// 			width:$("#tabs").parent().width(), 
// 			height:left_page_height
// 		}); 
	    
	    //页面加载完成  设置tabs的宽高
	    $(function(){ 
			$("#"+tabPanel).tabs({ 
				width:$("#"+tabPanel).parent().width(), 
				height:left_page_height
			}); 
		}); 
		
		//页面缩放时，设置tabs 和 iframe的宽高
		function WindowResize(){
	    	left_page_height = $(document).height();
		
	        var tab = $('#'+tabPanel).tabs('getSelected');
			$('#'+tabPanel).tabs({
	            width:$("#"+tabPanel).parent().width(), 
	            height:left_page_height
		    }); 
		    $('#'+tabPanel).tabs('select',tab.panel('options').title);
		    
		    //设置iframe
		    TabSelected();
		}
		
		//选中tab时，设置iframe的宽高
		function TabSelected(){
			var tab = $('#'+tabPanel).tabs('getSelected');
			// 获取选中的 tab panel 和它的 tab 对象   
			if(tab && tab.find('iframe').length > 0){   
				var iframe = tab.find('iframe')[0]; 
				iframe.style.height=left_page_height-40;
				iframe.style.width='100%';
			}  
		}
		
		//获取iframe所包含页面的高度
		function getInnerHeight() {
			return $('#'+tabPanel).tabs('getSelected').find('iframe')[0].contentWindow.document.body.scrollHeight;
		}
		//获取iframe所包含页面的宽度
		function getInnerWidth() {
			return $('#'+tabPanel).tabs('getSelected').find('iframe')[0].contentWindow.document.body.scrollWidth;
		}
    	    
		//添加标签页  为防止多次点击打开同一标签页，打开之前先进行判断，如果存在则刷新，不存在则创建  贺波 2012.3.6
		function addTab(id,title,url,closable){
		   if(!$('#'+tabPanel).tabs('exists',title)){		
				$('#'+tabPanel).tabs('add',{
				    id:id,
					title:title,
					style:{padding:'1px'},
	                content:'<iframe name="'+id+'" src="'+url+'" scrolling="auto"  onload="loadFrame(this)"  frameborder="0" style="width:100%;height:99%;"></iframe>',
	                closable:closable=='1'?true:false,
			        fit:true,
			        selected:true
				});
		    }else{ 
		        $("#"+tabPanel).tabs('select',title);		    
		        var tab = $('#'+tabPanel).tabs('getTab',title);
                $("#"+tabPanel).tabs('update',{
			        tab:tab,
			        options:{
			           title:title,
			           style:{padding:'1px'},
			           content:'<iframe name="'+id+'"   onload="loadFrame(this)"  src="'+url+'" scrolling="auto" frameborder="0" style="width:100%;height:99%;"></iframe>',
	                   closable:closable=='1'?true:false,
			           fit:true,
			           selected:true
			         }
                });		    
		    }
		    //设置一下选中tab的高度、宽度
		    WindowResize();
		}

	</script>
</html>
