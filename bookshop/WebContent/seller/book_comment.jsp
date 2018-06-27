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
	
	<style type="text/css">
		div{
			border: solid 0px;
		}
	</style>
	<script type="text/javascript">
			$(function(){
				$(".shut").bind("click",function(){
					$(".popBookInfo").hide(1000);
					$(".popCommentInfo").hide(1000);
				});
			});
		
			
			function showBookInfo(t)
			{
				//$(t).parent().next().css("background-color","green");
				$(t).parent().next().css("z-index","1");
				$(".popBookInfo").hide();
				$(".popCommentInfo").hide(1000);
				$(t).parent().next().toggle(1000);
			}
	
		function test(t){
			//alert(t.parentNode.parentNode.tagName);
			
			//tr上隐藏了一个tbody标签，所以  <table>标签，是<tr>标签的爷爷
			$(t).parent().parent().next().css("z-index","1");
			
			$(".popCommentInfo").hide();
			$(".popBookInfo").hide(1000);
			$(t).parent().parent().next().toggle(1000);
		}
	
	</script>
</head>
<body>
		<%@ include file="nav.jsp" %>
		<%@ include file="nav2.jsp" %>
		
		<div class="container">
			<div class="panel panel-default" style="max-height: 1000px; overflow: auto;">
			    <div class="panel-heading">
			        <span class="panel-title">
			            		图书评论列表
			        </span>
			    </div>
			    <div class="panel-body">
					<c:forEach items="${requestScope.commentInfoList }" var="commentInfo">	    
			    		<div class="row">
							<div class="col-lg-4 col-sm-6 col-xs-12" >
								<img src=${commentInfo.bookInfo.imgAddress1} style="max-height: 130px" width="auto" onclick="showBookInfo(this)"/>
								<p>书名：${commentInfo.bookInfo.name}</p>
								<p>编号：${commentInfo.bookInfo.b_no }</p>
							</div>
							<c:set var="book" value="${commentInfo.bookInfo }"></c:set>
							<div  class="popBookInfo" style="display: none;position: fixed; top: 200px;left: 300px; border: solid 0px; width: 50%; ">
				    			<div class="jumbotron" style="height: 500px; border: solid 4px blue;">
							        <button class="btn btn-primary shut" style="position: absolute; top: 3px; right: 3px;">关闭</button>
							        <div class="col-lg-6 col-sm-6 col-xs-12" style="height: 100%;">
							        	<img src=${book.imgAddress1}   style="max-height: 300px; max-width: 90%;"/>
							        	<p style="position: absolute; bottom: 5px;">
								        	<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=是">上架</a>
								        	<a class="btn btn-primary" href="/bookshop/sellBook.bookAction?b_no=${book.b_no}&isSell=否">下架</a>
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
							
							<div class="com-lg-8 col-sm-8 col-xs-12">
								<c:forEach items="${commentInfo.commentList}" var="comment">
									<table class="table table-hover" style="margin: 0px">
										<tr onclick="test(this)">
											<td><strong>${comment.c_name}(${comment.c_id})：</strong></td><td><small>${comment.comment_info }</small></td>
											<td>${comment.dateTime}</td>
										</tr>
									</table>
									<div  class="popCommentInfo" style="display: none;position: fixed; top: 200px;left: 300px; border: solid 0px; width: 50%; ">
						    			<div class="jumbotron" style="height: 400px; border: solid 4px blue;">
									        <button class="btn btn-primary shut" style="position: absolute; top: 3px; right: 3px;">关闭</button>
									        <strong>${comment.c_name}(${comment.c_id})：</strong>
												<p>${comment.comment_info}</p> 					       
									   </div>
						    		</div>
								</c:forEach>
							</div>
			    		</div>
			    		<hr/>
		    		</c:forEach>
			    
					
			    </div>
			</div>
		</div>
		
</body>
</html>