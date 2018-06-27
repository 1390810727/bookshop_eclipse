<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
		<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="/bookshop/js/bootstrap.js" ></script>
		<style>
			#content1 #content2{
				height: 800px;
			}
			.book_info>p{
				margin: 0px 0px;
				padding: 0px 0px;
			
			}
			.book_info{
				padding: 10px;
				background-color:#FDF5E6; 
			}
			
		</style>
		
		<script type="text/javascript">
		
			$(function(){
				$(".shut").bind("click",function(){
					$(".popBookInfo").fadeOut(1000);
				});
			});
		
			
			function showBookInfo(t)
			{
				//$(t).parent().next().css("background-color","green");
				$(t).parent().next().css("z-index","1");
				$(".popBookInfo").hide();
				$(t).parent().next().fadeIn(1000);
			}
			/* $(function(){
				var bodys=document.getElementsByTagName("html");
				body=bodys[0];
				body.onclick=function(e){
					//alert(e.target);
					if(e.target.className!="popBookInfo" && e.target.tagName.toLowerCase()!="img".toLowerCase())
						{
							$(".popBookInfo").hide(1000);
						}else{
						}
				}
			}); */
			
			
			
			function changeSellPrice(b_no, sellPrice){
				var result=prompt("请输入新的售价",sellPrice);
			
				if(result != null && ! isNaN(result) ){
					//如果是数字，修改金额，并刷新界面
					window.location="/bookshop/changeSellPrice.bookAction?b_no="+b_no+"&newSellPrice="+result;
				}else{
					alert("请输入的不是数字,请重新尝试");
				}
				
			}
		</script>
		
	</head>
	<body>
		<%@include file="nav.jsp" %>
		<%@include file="nav2.jsp" %>
		
		<!--主要页面-->
		
		
		<div class="container">
			<div class="panel panel-default" style="max-height: 400px; overflow: auto;">
			    <div class="panel-heading">
			        <span class="panel-title">
			            		已经上架的图书
			        </span>
			    </div>
			    <div class="panel-body">
			    	<c:forEach var="book" items="${requestScope.sellBookList }">
						<div class="col-lg-3 col-sm-6 col-xs-12 book_info" >
							<img src=${book.imgAddress1} style="height: 200px" width="90%" onclick="showBookInfo(this)"/>
							<p>书名：${book.name }</p>
							<p>编号：${book.b_no }</p>
							<p>售价：${book.sell_price }</p>
						</div>
						<div  class="popBookInfo" style="display: none;position: fixed; top: 50%; margin-top: -250px;
								left: 50%; margin-left: -400px ;border: solid 0px; 
								width: 800px; ">
			    			<div class="jumbotron" style="height: 500px; border: solid 4px; background-color:#3CB371;  box-shadow: 8px 8px 5px #BEE5EB; ">
						        <button class="btn btn-primary shut" style="position: absolute; top: 3px; right: 3px;">关闭</button>
						        <div class="col-lg-4 col-sm-6 col-xs-12" style="height: 100%;">
						        	<img src=${book.imgAddress1}  width="100%" style="max-height: 300px;"/>
						        	<p style="position: absolute; bottom: 5px;">
							        	<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=是">上架</a>
							        	<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=否">下架</a>
							        	<a class="btn btn-primary" href="" onclick="changeSellPrice(${book.b_no} , ${book.sell_price})" >修改售价</a>
						        	</p>
						        </div>
						        <div class="col-lg-8 col-sm-6 col-xs-12">
						        	<table class="table">
						        		<tr>
						        			<td>图书编号:</td><td>${book.b_no }</td>
						        		</tr>
						        		<tr>
						        			<td>图书名称:</td><td>${book.name }</td>
						        		</tr>
						        		<tr>
						        			<td>图书类型:</td><td>${book.type }</td>
						        		</tr>
						        		<tr>
						        			<td>作者:</td><td>${book.author}</td>
						        		</tr>
						        		<tr>
						        			<td>定价:</td><td>${book.price }</td>
						        		</tr>
						        		<tr>
						        			<td>售价:</td><td>${book.sell_price }</td>
						        		</tr>
						        		<tr>
						        			<td>库存数量:</td><td>${book.number }</td>
						        		</tr>
						        		<tr>
						        			<td>出版社:</td><td>${book.publish }</td>
						        		</tr>
						        		<tr>
						        			<td>出版日期:</td><td>${book.publish_date }</td>
						        		</tr>
						        		<tr>
						        			<td>图书描述:</td><td>${book.book_info }</td>
						        		</tr>
						        		<tr>
						        			<td>是否上架:</td><td>${book.isSell }</td>
						        		</tr>
						        	</table>
						        </div>
						        
						        
						   </div>
			    		</div>
			    	</c:forEach>
					
			    </div>
			</div>
		</div>
		<div class="container">
			<div class="panel panel-default" style="max-height: 400px; overflow: auto;">
			    <div class="panel-heading">
			        <span class="panel-title">
			            		未上架的图书
			        </span>
			        <a href="#" style="float: right; margin-right: 30px">修改</a>
			    </div>
			     <div class="panel-body">
			    	<c:forEach var="book" items="${requestScope.unSellBookList }">
						<div class="col-lg-3 col-sm-6 col-xs-12 book_info" >
							<img src=${book.imgAddress1} style="height: 200px" width="100%" onclick="showBookInfo(this)"/>
							<p>书名：${book.name }</p>
							<p>编号：${book.b_no }</p>
							<p>售价：${book.sell_price }</p>
						</div>
			    		<div id='${book.b_no}' class="popBookInfo" style="display: none;position: fixed; top: 50%; margin-top: -250px;left: 50%; margin-left: -400px; border: solid 0px; width: 800px; overflow: auto;">
			    			<div class="jumbotron" style="height: 500px; border: solid 4px blue;">
						        <button class="btn btn-primary shut" style="position: absolute; top: 3px; right: 3px;">关闭</button>
						        <div class="col-lg-4 col-sm-6 col-xs-12" style="height: 100%;">
						        	<img src=${book.imgAddress1}  width="100%" style="max-height: 300px;"/>
						        	<p style="position: absolute; bottom: 5px;">
						        		<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=是">上架</a>
						        		<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=否">下架</a>
						        		<a class="btn btn-primary" href="" onclick="changeSellPrice(${book.b_no} , ${book.sell_price})" >修改售价</a>
					        		</p>
						        </div>
						        <div class="col-lg-8 col-sm-6 col-xs-12">
						        	<table class="table">
						        		<tr>
						        			<td>图书编号:</td><td>${book.b_no }</td>
						        		</tr>
						        		<tr>
						        			<td>图书名称:</td><td>${book.name }</td>
						        		</tr>
						        		<tr>
						        			<td>图书类型:</td><td>${book.type }</td>
						        		</tr>
						        		<tr>
						        			<td>作者:</td><td>${book.author}</td>
						        		</tr>
						        		<tr>
						        			<td>定价:</td><td>${book.price }</td>
						        		</tr>
						        		<tr>
						        			<td>售价:</td><td>${book.sell_price }</td>
						        		</tr>
						        		<tr>
						        			<td>库存数量:</td><td>${book.number }</td>
						        		</tr>
						        		<tr>
						        			<td>出版社:</td><td>${book.publish }</td>
						        		</tr>
						        		<tr>
						        			<td>出版日期:</td><td>${book.publish_date }</td>
						        		</tr>
						        		<tr>
						        			<td>图书描述:</td><td>${book.book_info }</td>
						        		</tr>
						        		<tr>
						        			<td>是否上架:</td><td>${book.isSell }</td>
						        		</tr>
						        	</table>
						        </div>
						        
						        
						   </div>
			    		</div>
			    	</c:forEach>
			    </div>
			</div>
		</div>
	</body>
</html>
