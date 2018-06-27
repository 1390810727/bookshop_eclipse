<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
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
					$(".popBookInfo").hide(1000);
				});
			});
		
			
			function showBookInfo(t)
			{
				//$(t).parent().next().css("background-color","green");
				$(t).parent().next().css("z-index","1");
				$(".popBookInfo").hide();
				$(t).parent().next().toggle(1000);
			}
		
		</script>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<%@ include file="nav2.jsp" %>
	<div class="container">
		<div class="panel panel-default" style="max-height: 400px; overflow: auto;">
				    <div class="panel-heading">
				        <span class="panel-title">
				            		查询结果如下
				        </span>
				    </div>
				    <div class="panel-body">
				    	<c:forEach var="book" items="${requestScope.bookSearchList }">
							<div class="col-lg-3 col-sm-6 col-xs-12 book_info" >
								<img src=${book.imgAddress1} style="height: 150px" width="100%" onclick="showBookInfo(this)"/>
								<p>书名：${book.name }</p>
								<p>编号：${book.b_no }</p>
								<p>售价：${book.sell_price }</p>
							</div>
							<div  class="popBookInfo" style="display: none;position: fixed; top: 200px;left: 300px; border: solid 0px; width: 50%; ">
				    			<div class="jumbotron" style="height: 500px; border: solid 4px blue;">
							        <button class="btn btn-primary shut" style="position: absolute; top: 3px; right: 3px;">关闭</button>
							        <div class="col-lg-6 col-sm-6 col-xs-12" style="height: 100%;">
							        	<img src=${book.imgAddress1}  width="100%" style="max-height: 300px;"/>
							        	<p style="position: absolute; bottom: 5px;">
							        		<c:if test="${book.isSell=='是' }">
									        	<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=否">下架</a>
							        		</c:if>
							        		<c:if test="${book.isSell=='否'}">
									        	<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=是">上架</a>
							        		</c:if>
							        	</p>
							        </div>
							        <div class="col-lg-6 col-sm-6 col-xs-12">
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
	</div>
</body>
</html>