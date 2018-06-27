function addBook(s_no,b_no,number){
	//post请求的演示
	var xmlhttp;
	if(window.XMLHttpRequest)
		{
			xmlhttp=new XMLHttpRequest();
		}else{
			xmlhttp=ActiveXObject("Mircosoft.XMLHTTP");
		}
	//1.开启连接，其中第一个参数有get和post两种，第二个参数为请求的服务器地址，第三个为异步或同步，当为true时异步
	//当为false时，为同步，请求服务器时，浏览器处于等待状态，；等待服务器给出响应。而异步时则截然相反，请求服务时
	//浏览器还可以做其他事，服务在后台进行;
	xmlhttp.open("post","/bookshop/addBook.shopCardAction",true);
	//2.绑定事件，每当浏览器的readyState状态发生变化便会执行此绑定事件
	xmlhttp.onreadystatechange=function(){
		//readyState和state为xmlhttp的对象,readyState有四个值：0、1、2、3、4，当为4时代表服务器响应了浏览器
		//state为服务器的状态，有：200、500、404等，其中200代表服务器响应正常.
		//alert(xmlhttp.readyState+"|"+xmlhttp.status);
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				//接受服务器响应的信息					
				var text=xmlhttp.responseText;
				if(text=="添加成功"){
					$("#isAddBook").fadeIn(1000).fadeOut(2000);
				}else if(text=="添加失败"){
					$("#isAddBook").text("加入失败").fadeIn(1000).fadeOut(2000);
				}
				
			}
	}
	//3.设置头部
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	//4.发送请求,其中send中的参数为请求的数据，但是get方式应为空（get方式为地址传参），而post不能为空，为具体的键值对；
	//参数中给定键值对send("id=yuankui&pwd=123456")
	xmlhttp.send("s_no="+s_no+"&b_no="+b_no+"&number="+number);	
}

//异步向服务器发送请求，获得该顾客的 购物车所有图书信息
function showShopCard(){
	$.ajax({
		url:"/bookshop/loadBook.shopCardAction",
		type: "post",
		dataType: "text",
		success: function(result){
			
					//alert(result);
					if( "加载失败" != result){
						var resObject=JSON.parse(result);
						//alert("列表的长度:"+resObject.length);
						$("#bookPad").empty();
						for(var i=0;i<resObject.length;i++){
							var content='<div class="col-lg-4 col-sm-6 col-xs-12" style="height: 111px; position: relative; overflow: hidden;">'
								+'<div class="col-lg-4 col-sm-6 col-xs-12 bookImg">'
								+'	<img src="'+resObject[i].imgAddress1+'"  />'
								+'</div>'
								+'<div class="col-lg-8 col-sm-6 col-xs-12 bookInfo" >'
								+'	<input type="checkbox" onchange="change_result(this);" class="check_flag" style="position: absolute; right: 5px; top: 0px;"/>'
								+'	<input type="hidden" value="'+resObject[i].shopCard.s_no+'">'
								+'	<p>店铺：<span>'+resObject[i].shopName+'</span></p>'
								+'	<p>书名：<span>'+resObject[i].bookName+'</span></p>'
								+'	<p>编号：<span>'+resObject[i].shopCard.b_no+'</span></p>'
								+'	<p>单价：<span>'+resObject[i].price+'</span></p>'
								+'	<p>数量：<button onclick="reduceNumber(this)" style="border-radius: 5px; border: 1px; margin-right: 10px"> - </button><span onchange="change_result()">'+resObject[i].shopCard.number+'</span><button onclick="addNumber(this)" style="border-radius: 5px; border: 1px; margin-left: 10px"> + </button></p>'
								+'</div>'
								+'</div>';
							$("#bookPad").append(content);
						}
					}else{
						
					}
				}
	
	});
}


//修改购物车中的图书的数量（数量加1）

var reduceId;//创建一个全局变量，用来停止定时器
function reduceNumber(t){
	//先将html上的数量修改，然后将修改后的数量提交到服务器，修改数据库
	//采用ajax操作
	var num=parseInt($(t).next().text());//数量
	var s_no=$(t).parent().parent().children().eq(1).val();
	var b_no=$(t).parent().parent().children().eq(4).children().text();
	num=num-1;
	$(t).next().text(num);
	clearTimeout(reduceId);
	reduceId=setTimeout(function(){
		changeNumber(s_no,b_no,num);
		change_result();
	},1500);
	
	
}

var addId;
function addNumber(t){
	//先将html上的数量修改，然后将修改后的数量提交到服务器，修改数据库
	//采用ajax操作
	var num=parseInt($(t).prev().text());
	var s_no=$(t).parent().parent().children().eq(1).val();
	var b_no=$(t).parent().parent().children().eq(4).children().text();
	num=num+1;
	$(t).prev().text(num);
	clearTimeout(addId);
	addId=setTimeout(function(){
		changeNumber(s_no,b_no,num);
		change_result();
	},1500);
}
function changeNumber(s_no,b_no,num){
	
	$.ajax({
		url:"/bookshop/changeNumber.shopCardAction",
		type: "post",
		dataType: "text",
		data: "s_no="+s_no+"&b_no="+b_no+"&number="+num,
		success: function(result){
			if("成功"==result.trim()){
				
			}else {
				alert("数量修改失败，未知错误");
			}
		}
	});
	
}