function createPages(total, curPage) {
	if (typeof total != "number") {
		alert("查询数据有问题,请联系管理员!!!!!");
		return;
	}
	var count = total % rows;
	var pages = 0;
	if (count == 0) {
		pages = total / rows;
	} else {
		pages = (total - count) / rows + 1
	}
	createPagesLi(pages, curPage);
}

var page={
		index:1,//循环开始
		size:0,//循环结束
		flag:10,//最开始显示分页个数
		num:1,//浮动数字
		getData:function(p, c){
			debugger;
			if (typeof c != "number") {
				c = 1;
			}
			if(typeof p != "number"){
				p=1;
			}
			this.c=c,this.p=p;
//			if(this.p<=this.flag){
//				this.index=1;
//				this.size=this.p
//				return this;
//			}
			if (this.c > this.p) {//当前页等于最大页数
				this.index = c - (this.flag+this.num);//开始页减去设定显示的个数
				this.size = p;//for循环的最大数
			}else if (c > (this.flag -this.num)) {//
				this.index = this.c - this.flag/2;//
				this.size = this.index + this.flag/2;//
			}else if(c <= (this.flag -this.num)){
				this.index=1;
				this.size=this.flag;
			}
			if(this.size-this.index<this.flag){//如果最大循环数减去开始循环数小于最开始页面显示个数,开始循环数等于最大循环数减去最开始页面显示个数
				this.size=this.index+this.flag;
			}
			if (this.size > p) {//如果最大循环数大于最大页数
				this.size = p;
				this.index=this.size-(this.flag+this.num);
			}
			if (this.index <= 0) {//如果循环开始页数小于0,将其赋值为1
				this.index = 1;
			}
			if (this.size <= 0) {//如果最大循环数小于0,将其赋值为1
				this.size = 1;
			}
			return this;
		}
}
function createPagesLi(pages, curpage,t) {
	var p=page.getData(pages, curpage);
//	debugger;
	t.empty();
	var indexPage = document.createElement("li");
	$indexPage = $(indexPage);
	$indexPage.addClass("pageLi");
	$indexPage.attr("onclick", 'javascript:initData(1)');
	$indexPage.html("首页");
	t.append($indexPage);
	for (var i = p.index; i <= p.size; i++) {
		var li = document.createElement("li");
		$li = $(li);
		if (i  == p.c) {
			$li.addClass( "selLi");
			$li.html(i);
		} else {
			$li.addClass("pageLi");
			$li.attr("onclick", 'javascript:initData(' + i  + ')');
			$li.html(i);
		}
		t.append($li);
	}

	if (p.p > p.flag-3) {
		var endPage = document.createElement("li");
		//var oo = document.createElement("li");
		//$oo = $(oo);
		//$oo.attr("class", "pageLi");
		//$oo.html("....");
		$endPage = $(endPage);
		$endPage.addClass( "pageLi");
		$endPage.attr("onclick", 'javascript:initData(' + p.p + ')');
		$endPage.html("末页");
		//$("#page").append($oo);
		t.append($endPage);
	}
}
var initData=function(cur){
	createPagesLi(ps,cur,$('#page'));
}
function createPagesLi2(pages, curpage,t) {
	var p=page.getData(pages, curpage);
//	debugger;
	t.empty();
	var indexPage = document.createElement("li");
	$indexPage = $(indexPage);
	$indexPage.addClass( "btn btn-default");
	$indexPage.attr("onclick", 'javascript:initData2(1)');
	$indexPage.html("首页");
	t.append($indexPage);
	for (var i = p.index; i <= p.size; i++) {
		var li = document.createElement("li");
		$li = $(li);
		if (i  == p.c) {
			$li.addClass("btn btn-default active");
			$li.html(i);
		} else {
			$li.addClass("btn btn-default");
			$li.attr("onclick", 'javascript:initData2(' + i  + ')');
			$li.html(i);
		}
		t.append($li);
	}

	if (p.p > p.flag-3) {
		var endPage = document.createElement("li");
		//var oo = document.createElement("li");
		//$oo = $(oo);
		//$oo.attr("class", "pageLi");
		//$oo.html("....");
		$endPage = $(endPage);
		$endPage.addClass("btn btn-default");
		$endPage.attr("onclick", 'javascript:initData2(' + p.p + ')');
		$endPage.html("末页");
		//$("#page").append($oo);
		t.append($endPage);
	}
}
var initData2=function(cur){
	createPagesLi(ps,cur,$('#page2'));
}


