<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
	<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="/bookshop/js/bootstrap.js" ></script>		
	<script type="text/javascript" src="/bookshop/main/shopCard.js" ></script>
	<title>物流信息</title>
</head>
<body>
		<%@ include file="nav.jsp" %>
		<%@ include file="nav_right.jsp" %>
		<div class="container">
			<%@ include file="nav_orderForm.jsp" %> 
		   <div class="jumbotron">
		   		<table class="table">
		   			<thead>
		   				<th>地址</th><th>更新时间</th>
		   			</thead>
		   			<tbody>
				   		<c:forEach  items="${requestScope.logisticList}" var="logistic">
				   			<tr>${logistic.address}</tr><tr>${logistic.date}</tr>
				   		</c:forEach>
		   			</tbody>
		   		</table>
		   </div>
		</div>
	
</body>
</html>