var menuTreeObj = null,groupTreeObj=null;


/**********************************************
 * 
 * 菜单树
*
**********************************************/

 /**
  * yanlei
 * @param id
 * @returns
 */
function initMenuTree(groupId){
     var menuTreeNodes ;
     //获取数据
     $.ajax({
    	  async : false,
          cache:false,
          type: 'get',
          dataType : "json",
          url: basePath+"menu/getTree.do",//请求的action路径
          data:{groupId:groupId},
          error: function () {//请求失败处理函数
	          alert('请求失败');
          },
          success:function(data){ //请求成功后处理函数。  
        	  menuTreeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes
          }
      });
     //定义tree样式
      var setting = {
	        isSimpleData : true,              //数据是否采用简单 Array 格式，默认false
            treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性
            treeNodeParentKey : "pId",        //在isSimpleData格式下，当前节点的父节点id属性
            showLine : true,                //是否显示节点间的连线
            check:{
            	enable: true,
            	nocheckInherit: false,
            	chkStyle:"checkbox"
            }, 
            data: {
            	simpleData: {enable: true}
            },
            callback:{
//               onClick: onClick,
//               onRightClick: zTreeOnRightClick
            }
      };
      menuTreeObj=$.fn.zTree.init($("#MenuTree"), setting, menuTreeNodes);
}

 
function expandAllMenuTree(isExpand) {
	menuTreeObj.expandAll(isExpand);
}
	    
function saveMenuSet(){
	var groupId="",ids=[];
//	var objs=menuTreeObj.getChangeCheckedNodes();//获取check改变的数据
	var objs=menuTreeObj.getCheckedNodes(true);
	var group=groupTreeObj.getSelectedNodes();//获取选中
//	console.log(objs)
//	console.log(objs1)
	for (var i = 0; i < objs.length; i++) {
		if(objs[i].id=='-1'){
			continue;
		}
		ids.push(objs[i].id);
	}
//	console.log(ids)
	groupId=group[0].id
//	console.log(groupId)

	 //获取数据
    $.ajax({
   	  async : false,
         cache:false,
         type: 'POST',
         dataType : "json",
         url: basePath+"menu/handleMenuGroup.do",//请求的action路径
         data:{groupId:groupId,ids:ids.join(",")},
         error: function () {//请求失败处理函数
	          alert('请求失败');
         },
         success:function(data){ //请求成功后处理函数。  
        	 if(data>=0){
        		 alert("保存成功");
        	 }else{
        		 alert("保存失败");
        	 }
         }
     });
}

 

/**********************************************
* 
* 角色树
*
**********************************************/


/**
 * yanlei
* @param id
* @returns
*/
function initGroupTree(){
    var groupTreeNodes ;
    //获取数据
    $.ajax({
   	  async : false,
         cache:false,
         type: 'POST',
         dataType : "json",
         url: basePath+"group/getList.do",//请求的action路径
         data:{status:"1"},
         error: function () {//请求失败处理函数
	          alert('请求失败');
         },
         success:function(data){ //请求成功后处理函数。  
        	 groupTreeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes
         }
     });
    //定义tree样式
     var setting = {
	        isSimpleData : true,              //数据是否采用简单 Array 格式，默认false
           treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性
           treeNodeParentKey : "pId",        //在isSimpleData格式下，当前节点的父节点id属性
           showLine : true,                //是否显示节点间的连线
           check:{
           }, 
           data: {
           		simpleData: {enable: true}
           },
           callback:{
              onClick: onClick
           }
     };
     groupTreeObj=$.fn.zTree.init($("#GroupTree"), setting, groupTreeNodes);
     selFirst();
}

 /**
  * 授权对象树全部展开
  */
 function expandAllGroupTree(isExpand){
 	privsTreeObj.expandAll(isExpand);
 }

 function onClick(event, treeId, treeNode, clickFlag) {
	 var isParent=treeNode.isParent;
	 
	 if(isParent){
		 alert("角色不可以选择根节点");
		 selFirst();
		 return ;
	 }
//	 console.log(isParent);
//	 console.log(treeId);
//	 console.log(treeNode);
//	 console.log(treeNode.id);
	 initMenuTree(treeNode.id);
 }	
 
 
function selFirst(){
	  //默认选择第一个节点
	 	var arrayNodes = groupTreeObj.transformToArray(groupTreeObj.getNodes());
	 	var length = arrayNodes.length;
	 	if(length>1){
	 		groupTreeObj.selectNode(arrayNodes[1]);
	 		initMenuTree(arrayNodes[1].id)
	 	}
}
 
