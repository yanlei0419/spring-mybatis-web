
//打开弹窗 cyy 2014.1.18
//第一个参数url表示弹窗的地址（必输）
//第二个参数width表示弹窗的宽度（选填，默认值800）
//第三个参数height表示弹窗的高度（选填，默认值500）
//第四个参数name表示弹出窗口的名字，（选填，默认值为空字符串）
function openWin(url,width,height,name){
	var url =url; //转向网页的地址;
	var iWidth = 800; //弹出窗口的宽度;
	var iHeight = 500; //弹出窗口的高度;
	var iName = ''; //网页名称，可为空;
    
    //如果指定了宽、高、名字，则用指定的
    if(width){
       iWidth = width; 
    }
    if(height){
       iHeight = height; 
    }
    if(name){
       iName = name; 
    }
    
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
    window.open(url, iName, 'width='+iWidth+', height='+iHeight+', toolbars=no, top='+iTop+',left='+iLeft+', scrollbars=yes, location=no, status=no, menubar=no, resizable=no');
    
}

