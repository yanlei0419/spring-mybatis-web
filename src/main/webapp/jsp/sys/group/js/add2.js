			function senfe(o,a,b,c,d){
				var t=document.getElementById(o).getElementsByTagName("tr");
				for(var i=0;i<t.length;i++){
					t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
				}
			}
			$(document).ready(function(){
				senfe("Group","#f3f8fd","#ffffff","#ADADAD","#f391a9");
				$.formValidator.initConfig( {
					formID : "add",
					theme : 'SolidBox',
//					mode : 'AutoTip',
					inIframe : true,
					onError:function(msg,obj,errorlist){
						
					}
				});
				$("#groupName")
					.formValidator({
						empty:false,
						onShow:"请输入角色名",
						onFocus:"请输入角色名",
						onCorrect:"您输入的角色名合法"
					})
					.inputValidator({
						min:1,
						max:25,
						onError:"角色名有误,请确认"
					});
		
				$("#remark")
					.formValidator({
						empty:true,
						onShow:"请输入备注",
						onFocus:"请输入备注",
						onCorrect:"您输入的备注合法"
					})
					.inputValidator({
						//min:1,
						max:4000,
						onError:"备注有误,请确认"
					});
		
		
			});
			$(window).resize(function(){
				$.formValidator.reloadAutoTip();
				$('#GroupPanel').panel('resize');
			});
	
