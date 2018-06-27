<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="js/bootstrap.js" ></script>		
		<style>
			div{	
				border: 0px solid;
			}
			#shopCardPad{
				border: 1px solid;
				border-radius: 10px 10px;
				background: green;
				width: 900px;
				height: 600px;
				position: fixed;
				top: -660px;
				left: 50%;
				margin-left: -450px;
				display: none;
				z-index: 99;
			}
			
			#bookPad{
				height: 555px;
				
			}
			
			#bookPad>div{
				padding: 0px;
				margin: 0px;
				border:  1px solid;
				border-radius: 10px 10px;
			}
			#btnGroup{
				height: 38px;
				margin-bottom: 7px;
			}
			.bookInfo>p{
				margin: 0px 0px;
				padding: 0px 0px;
			}
			.bookImg{
				padding: 0px;
				margin: 0px;
			}
			.bookImg>img{
				margin: 0px;
				padding: 0px;
				height: 111px;
				max-width: 100px;
			}
			
		</style>
		<script>
			$(function(){
				var i=0;
				
				$("#shop_card").bind("click",function(){
					//当i为偶数时，代表弹出购物车页面，这时应加载图书列表（调用showShopCard函数）
					if(i%2 == 0){
						showShopCard();
						
					}
					
					var shopPad=$("#shopCardPad").css("top");
					//alert(shopPad+"  ----"+shopPad.length);
					var topNumber=parseInt(shopPad.substr(0,shopPad.length-2));
					var arr=[-660,0];
					//alert(arr[(i=i+1)%2]);
					
					
					$("#shopCardPad").animate({top: arr[(++i)%2]+"px"}).toggle(500);
				});
				
				$("#checkAll").bind("click",checkAll);
				$("#unCheckAll").bind("click",unCheckAll);
				$("#deleteBook").bind("click",deleteBook);
			})
			function change_result(){
				var arr_check=$(".check_flag");
				//alert(arr_check.length);
				var numSum=0;
				var priceSum=0;
				for( i=0;i<arr_check.length;i++)
				{
					var checked=arr_check.get(i).checked;
					if(checked==true)
					{
						var s_no=$(arr_check.get(i)).next().val();
						var s_name=$(arr_check.get(i)).next().next().children("span").text();
						var b_name=$(arr_check.get(i)).next().next().next().children("span").text();
						var b_no=$(arr_check.get(i)).next().next().next().next().children("span").text();
						
						var price=parseFloat($(arr_check.get(i)).next().next().next().next().next().children("span").text());
						var num=parseInt($(arr_check.get(i)).next().next().next().next().next().next().children("span").text());
						numSum=numSum+num;
						priceSum=priceSum+num*price;
						//alert(numSum+"   "+priceSum);
//						alert(s_no+"  "+s_name+"  "+b_name+"  "+b_no+"  "+price+"  "+num);
					}
					//循环遍历完成之后，将总量  和总金额显示在指定位置
					
					$("#sumNumber").text(numSum);
					$("#sumPrice").text(priceSum);
				}
			}
			
			//全选所有复选框
			function checkAll(){
				var arr_check=$(".check_flag");
				for(var i=0;i<arr_check.length;i++){
					arr_check[i].checked=true;
				}
				
				change_result();
				
			}
			
			//全不选所有复选框
			function unCheckAll(){
				var arr_check=$(".check_flag");
				for(var i=0;i<arr_check.length;i++){
					arr_check[i].checked=false;
				}
				change_result();
				
			}
			
			function submitOrderForm(){
				var orderForm={};
				var jsonArr=[];
				var arr_check=$(".check_flag");
				for(var i=0;i<arr_check.length;i++){
					
					var checked=arr_check.get(i).checked;
					if(checked==true){
						var s_no=$(arr_check.get(i)).next().val();
						var b_no=$(arr_check.get(i)).next().next().next().next().children("span").text();
						var num=parseInt($(arr_check.get(i)).next().next().next().next().next().next().children("span").text());
						var price=parseFloat($(arr_check.get(i)).next().next().next().next().next().children("span").text());
						orderForm={};
						orderForm.s_no=s_no;
						orderForm.b_no=b_no;
						orderForm.number=num;
						orderForm.price=price;
						jsonArr.push(orderForm);
					}
				}
				var jsonStr=JSON.stringify(jsonArr);
				//alert("对象长度："+jsonArr.length+"   json字符串："+jsonStr);
				var form=document.createElement("form");
				var text_input=document.createElement("input");
				text_input.name="content";
				text_input.value=jsonStr;
				form.appendChild(text_input);
				form.action="/bookshop/submitOrderForm.c_orderFormAction";
				form.submit(); 

			} 
		function deleteBook(){
			var orderForm={};
			var jsonArr=[];
			var arr_check=$(".check_flag");
			for(var i=0;i<arr_check.length;i++){
				
				var checked=arr_check.get(i).checked;
				if(checked==true){
					var s_no=$(arr_check.get(i)).next().val();
					var b_no=$(arr_check.get(i)).next().next().next().next().children("span").text();
					//var num=parseInt($(arr_check.get(i)).next().next().next().next().next().next().children("span").text());
					//var price=parseFloat($(arr_check.get(i)).next().next().next().next().next().children("span").text());
					orderForm={};
					orderForm.s_no=s_no;
					orderForm.b_no=b_no;
				//	orderForm.number=num;
					//orderForm.price=price;
					jsonArr.push(orderForm);
				}
			}
			var jsonStr=JSON.stringify(jsonArr);
			$.ajax({
				url:"/bookshop/deleteBook.shopCardAction",
				type: "post",
				dataType: "text",
				data: "content="+jsonStr,
				success: function(result){
					var  flag='<div id="isDeleteBook"   style="border:1px solid red; '
							+'	color: white; background-color: #000000;  opacity:0.7; '
							+' width:200px;height:150px; text-align: center; display: none; '
							+' border-radius: 10px; font-size: 30px; line-height: 150px; z-index: 1000;'
							+' position:absolute;left:50%;margin-left:-100px;top:50%;margin-top:-75px;"> '
							+' 删除'+result+'条图书'
							+' </div> ';
					$("body").append(flag);
					$("#isDeleteBook").fadeIn(4000).fadeOut(2000);
					showShopCard();
				},
				error: function(error){
					alert(error);
				}
			});
			
			
		}	
			
		</script>
		<div id="shopCardPad">
			<div id="bookPad" style="overflow: auto;">
				
					
				
			</div>
			<div id="btnGroup">
				<div class="btn-group">
					<button class="btn btn-default" id="checkAll">全选</button>
					<button class="btn btn-default" id="unCheckAll">全不选</button>
					<button class="btn btn-default" id="deleteBook">删除</button>
				</div>
				<span style="margin-left: 40px;">共计：</span><span id="sumNumber">		</span><span>件商品</span>
				<span style="margin-left: 40px;">合计：</span><span id="sumPrice"> 		</span><span>元钱</span>
				<div class="btn-group" style="float: right; margin-right: 5px;">
					<button class="btn btn-danger" onclick="submitOrderForm();">提交</button>
				</div>
			</div>			
		</div>
