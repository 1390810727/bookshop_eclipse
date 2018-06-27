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
	<script type="text/javascript" src="/bookshop/main/shopCard.js"></script>
	<style type="text/css">
		div{
			border: solid 0px;
		}
		.container>div{
			 height: 900px;
			 border: solid 0px;
		}
		.paging{
			position: absolute;
			bottom: 10px;
			right: 40%;
		}
	
	</style>
	
	<script type="text/javascript">
		//实现上一页的函数
		function previousPage(){
			var pageFlag=$("#page_flag").val();
			var pageNumber=parseInt($("#page_number").val());
			if(pageNumber<=0){
				return;
			}
			switch(pageFlag)
			{
				case "mainPage":
						pageNumber--;
						window.location="/bookshop/randomBook?pageNumber="+pageNumber;
					break;
				case "searchName":
					pageNumber--;
					var searchName=$("#searchName").val();
					window.location="/bookshop/searchByName.searchBookAction?"
							+"pageNumber="+pageNumber+"&searchName="+searchName;
					break;
				case "searchType":
					pageNumber--;
					var searchContent=$("search_content").val();
					window.location="/bookshop/searchByType.searchBookAction?"
										+"pageNumber="+pageNumber+"&searchContent="+searchContent;
			}
		}	
		
		function nextPage(){
			var pageFlag=$("#page_flag").val();
			var pageNumber=$("#page_number").val();
			alert(pageNumber+"   "+pageFlag);
			switch(pageFlag)
			{
				case "mainPage":
					pageNumber++;
					alert(pageNumber);
					window.location="/bookshop/randomBook?pageNumber="+pageNumber;
					break;
				case "searchName":
					pageNumber++;
					var searchName=$("#searchName").val();
					window.location="/bookshop/searchByName.searchBookAction?"
							+"pageNumber="+pageNumber+"&searchName="+searchName;
					break;
				case "searchType":
					pageNumber++;
					var searchContent=$("search_content").val();
					window.location="/bookshop/searchByType.searchBookAction?"
										+"pageNumber="+pageNumber+"&searchContent="+searchContent;
			}
			
		}
		
		//根据类型来查找
		function searchType(t){
			var searchType=t.innerText;
			var pageNumber=0;
			window.location="/bookshop/searchByType.searchBookAction?"
						+"pageNumber="+pageNumber+"&searchType="+searchType;
		}
	
	</script>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<%@ include file="lunbo.jsp" %>
	<!-- 主页的标志，因为该页面被多次利用（分别来自不同的sevlet），所以需要用一个标志来告诉我们，该
		页面的请求是哪个
	 -->
	 <input type="hidden" id="page_flag" value="${requestScope.pageFlag}">
	 
	 <!--上一次搜索的内容  -->
	 
	 <input type="hidden" id="search_content" value="${requestScope.searchContent}">
	<div class="container">
		<div class="col-lg-3 col-sm-3 col-xs-3">
			<div class="col-lg-12 col-sm-12 col-xs-12" style=" height:450px;">
			
				    <div class="panel-body">
				        <a onclick="searchType(this)" class="list-group-item active">文学</a>
						<a onclick="searchType(this)" class="list-group-item">小说</a>
						<a onclick="searchType(this)" class="list-group-item">考试</a>
						<a onclick="searchType(this)" class="list-group-item">计算机</a>
						<a onclick="searchType(this)" class="list-group-item">外语</a>
						<a onclick="searchType(this)" class="list-group-item">艺术</a>
						<a onclick="searchType(this)" class="list-group-item">历史</a>
						<a onclick="searchType(this)" class="list-group-item">法律</a>
						<a onclick="searchType(this)" class="list-group-item">励志</a>
						<a onclick="searchType(this)" class="list-group-item">漫画</a>
				    </div>
				
			</div>
			<div class="col-lg-12 col-sm-12 col-xs-12" style="height: 450px">
				<div class="panel panel-primary">
				    <div class="panel-heading">
				        <h3 class="panel-title">畅销图书</h3>
				    </div>
				    <div class="panel-body" style="padding: 0px;">
				    	<c:forEach var="bookInfo" items="${bookInfoList }">
				    	
					     	<a href="#" class="list-group-item">${bookInfo.name }</a>
				    	</c:forEach>
				    </div>
				</div>
			</div>
		</div>
		<div class="col-lg-9 col-sm-9 col-xs-9">
		
			
			<c:forEach items="${requestScope.recomendBookList}" var="recomendBookInfo">
			<c:set var="bookInfo" value="${recomendBookInfo.bookInfo}"></c:set>
				
				<div class="col-lg-4 col-sm-6 col-xs-12">
			        <div class="thumbnail">
			            <img src="${bookInfo.imgAddress1}" alt="" style="height: 200px;">
			            <div class="caption">
			                <span>${bookInfo.name }</span><br>
			                <span style="font-size: 10px;color: red;">￥</span><font color="red" style="font-size: 18px;">${bookInfo.sell_price }</font>
			               <c:if test="${recomendBookInfo.number!=0 }">
			               
			                <span style="float: right; font-size: smaller	" class="text-muted">${recomendBookInfo.number}人付款</span>
			               </c:if>
			               
			                <br>
			                <p>		                   
			                	 <a  class="btn btn-default" onclick="addBook(${bookInfo.s_no},${bookInfo.b_no},1)">
	                    			    加入购物车
			                    </a>
			                	 <a  class="btn btn-default">
										直接购买
			                    </a>
			                </p>
			            </div>
			        </div>
			    </div>
				
			
			</c:forEach>
			
			
			
			<div class="paging">
				<ul class="pager">
				    <li><a href="" onclick="previousPage();">上一页</a></li>
				    <li><a href="" onclick="nextPage();">下一页</a></li>
				</ul>
			</div>
			
			<!--告诉服务器本页是第几页  -->
			<input type="hidden" id="page_number" value="${requestScope.pageNumber}">
		</div>
		
	
	
	</div>
	
	<div id="isAddBook"   style="border:1px solid red; 
			color: white; background-color: #000000;  opacity:0.7; 
			width:200px;height:150px; text-align: center; display: none;
			border-radius: 10px; font-size: 30px; line-height: 150px;
			position:absolute;left:50%;margin-left:-100px;top:50%;margin-top:-75px;">
			已加入购物车
		</div>
	
	<%@ include file="nav_right.jsp" %>
</body>
</html>