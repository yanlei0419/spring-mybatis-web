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
    	
	    $("#tabs").tabs({ 
			width:$("#tabs").parent().width(), 
			height:left_page_height
		}); 
	    
	    //页面加载完成  设置tabs的宽高
	    $(function(){ 
			$("#tabs").tabs({ 
				width:$("#tabs").parent().width(), 
				height:left_page_height
			}); 
            getCount();
            //caoyy 进入系统后，如果有未读系统消息则弹出右下角的弹窗
            /**
            if(count>0){
                $.ajax({
                    dataType:"text", async:false,  type:"post",        
                    url:'${pageContext.request.contextPath}MsgManage_getPList.do', 
                    data:{
<%--                         recipient:'<%=userId%>', --%>
                        tipType:'0'//系统消息
                    },    
                    success:function(data){
                         if(data!='0'){   
                             $.messager.lays(250, 150);
                             $.messager.show(0,data,100000);
                         }
                    }   
                });
            }
            **/
            Add();
		}); 
		
		//页面缩放时，设置tabs 和 iframe的宽高
		function WindowResize(){
	    	left_page_height = $(document).height();
		
	        var tab = $('#tabs').tabs('getSelected');
			$('#tabs').tabs({
	            width:$("#tabs").parent().width(), 
	            height:left_page_height
		    }); 
		    $('#tabs').tabs('select',tab.panel('options').title);
		    
		    //设置iframe
		    TabSelected();
		}
		
		//选中tab时，设置iframe的宽高
		function TabSelected(){
			var tab = $('#tabs').tabs('getSelected');
			// 获取选中的 tab panel 和它的 tab 对象   
			if(tab && tab.find('iframe').length > 0){   
				var iframe = tab.find('iframe')[0]; 
				iframe.style.height=left_page_height-40;
				iframe.style.width='100%';
			}  
		}
		
		//获取iframe所包含页面的高度
		function getInnerHeight() {
			return $('#tabs').tabs('getSelected').find('iframe')[0].contentWindow.document.body.scrollHeight;
		}
		//获取iframe所包含页面的宽度
		function getInnerWidth() {
			return $('#tabs').tabs('getSelected').find('iframe')[0].contentWindow.document.body.scrollWidth;
		}
    	    
		//添加标签页  为防止多次点击打开同一标签页，打开之前先进行判断，如果存在则刷新，不存在则创建  贺波 2012.3.6
		function addTab(id,title,url,closable){
		   //如果未打开该标签页，则新建该标签页 贺波 2012.3.6
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
      
		function update(){
			index++;
			var tab = $('#tabs').tabs('getSelected');
			$('#tabs').tabs('update', {
				tab: tab,
				options:{
					title:'new title'+index,
					iconCls:'icon-save'
				}
			});
		}
	
		$("#"+tabPanel).tabs({ 
              onSelect: function(tt) { 
                  lastTabs = $.grep(lastTabs, function(n, i) { return n != tt; }); 
                  //重新压入，保证 最新的在最上面 
                  lastTabs.push(tt); 
                  //更新当前            
                  currentTabTitle = tt; 
              }, 
              onClose: function(tt) { 
                  //移除 tt 
                  lastTabs = $.grep(lastTabs, function(n, i) { return n != tt; }); 
                  //重新选择 
                  $("#"+tabPanel).tabs("select", lastTabs[lastTabs.length - 1]); 
              } 
         }); 

		
		function getSelected(){
			var tab = $('#tabs').tabs('getSelected');
			alert('Selected: '+tab.panel('options').title);
		}
        
        //右下角的提示框 caoyy
        function Add(){
            /**
            var hCount=0;
            $.ajax({
                dataType:"text", async:false,  type:"post",        
                url:'${pageContext.request.contextPath}MsgManage_getCount.do', 
                data:{
<%--                     recipient:'<%=userId%>', --%>
                    hadView:'N',
                    tipType:'0'//系统消息
                
                },    
                success:function(data){
                    hCount=data;
                }   
            });
            if(hCount>count){
                $.ajax({
                    dataType:"text", async:false,  type:"post",        
                    url:'${pageContext.request.contextPath}MsgManage_getPList.do', 
                    data:{
<%--                         recipient:'<%=userId%>', --%>
                        tipType:'0'//系统消息
                    
                    },    
                    success:function(data){
                         if(data!='0'){   
                             $.messager.lays(250, 150);
                             $.messager.show(0,data,100000);
                             count = hCount;
                         }
                    }   
                });
            }
            setTimeout("Add()",4000);
            **/
        } 
        //右下角提示框的设置按钮    
        function Set(){
            openWin("${pageContext.request.contextPath}TipManage_getPersonalTip.do");
        }
        //右下角弹窗的更多按钮
        function More(){
            openWin("${pageContext.request.contextPath}hrm/msg/list.jsp?type=1","1100","600");
        }
        //点击消息标题，打开内容url中包含type=1可以根据此参数出去data-options中的title标题
        function openUrl(url,id){
            openWin(url);
            /**
            $.ajax({ 
                type : "POST", 
                url:'${pageContext.request.contextPath}MsgManage_markAsRead.do', 
                traditional: true,//在struts2下该属性必须有 
                data:{ids:id}, 
                success:function(result){ 
                }
            });
            **/
        }
        //获取用户自己未读的系统消息
        function getCount(){
            /**
            $.ajax({
                dataType:"text", async:false,  type:"post",        
                url:'${pageContext.request.contextPath}MsgManage_getCount.do', 
                data:{
                    recipient:'<%=userId%>',
                    hadView:'N',
                    tipType:'0'//系统消息
                
                },    
                success:function(data){
                    count=data;
                }   
            });
            **/
        }
	</script>
</html>
