<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
		<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="/bookshop/js/bootstrap.js" ></script>
		
		<style>
			div{
				
				/*border: solid 1px;*/
			}
			.container{
				width: 100%;
			}
			.container>div{
				
				height: 500px;
			}
			
		</style>
	</head>
	<body>
		<!--
        	作者：袁奎
        	时间：2017-11-13
        	描述：导航栏，包含查询操作，查询的结果跳到另一个网页
        -->
       <%@ include file="nav.jsp" %>
       <%@ include file="nav2.jsp" %>
	
		<h2>显示商家的基本信息，显示各个商家的月销售，日销售，年销售排行。包括销售额，销售量。
			还包括店铺的查找，搜索商家的姓名或id,然后查看其信息（主要包括销售信息，日销售，年销售等）</h2>
		<div class="container">
			<div class="col-xs-12 col-sm-6 col-lg-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<center><h3>今日销量排行</h3></center>
					</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<td>排行</td><td>店铺名</td><td>店铺号</td><td>图书<td>日销售量</td><td>销售额</td>
							</tr>
							
							<c:set var="i" value="1"></c:set>
							<c:forEach items="${requestScope.daySaleNumber}" var="sellerInfo">
								<tr>
									<td>${i}</td><td>${sellerInfo.shopName }</td><td>${sellerInfo.s_no}</td>
									<td>${sellerInfo.bookName }</td>
									<td>${sellerInfo.number }</td><td>${sellerInfo.amount}</td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-lg-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<center><h3>本月销量排行</h3></center>
					</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<td>排行</td><td>店铺名</td><td>商家</td><td>图书</td><td>月销售量</td><td>销售额</td>
							</tr>
							<c:set var="i" value="1"></c:set>
							<c:forEach items="${requestScope.monthSaleNumber}" var="sellerInfo">
								<tr>
									<td>${i}</td><td>${sellerInfo.shopName }</td><td>${sellerInfo.s_no}</td>
									<td>${sellerInfo.bookName }</td>
									<td>${sellerInfo.number }</td><td>${sellerInfo.amount}</td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-lg-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<center><h3>本年销量排行</h3></center>
					</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<td>排行</td><td>店铺名</td><td>商家</td><td>图书</td><td>年销售量</td><td>销售额</td>
							</tr>
							
							<c:set var="i" value="1"></c:set>
							<c:forEach items="${requestScope.yearSaleNumber}" var="sellerInfo">
								<tr>
									<td>${i}</td><td>${sellerInfo.shopName }</td><td>${sellerInfo.s_no}</td>
									<td>${sellerInfo.bookName }</td>
									<td>${sellerInfo.number }</td><td>${sellerInfo.amount}</td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-lg-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<center><h3>今日销售额排行</h3></center>
					</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<td>排行</td><td>店铺名</td><td>商家</td><td>图书</td><td>日销售量</td><td>日销售额</td>
							</tr>
							
							<c:set var="i" value="1"></c:set>
							<c:forEach items="${requestScope.daySaleAmount}" var="sellerInfo">
								<tr>
									<td>${i}</td><td>${sellerInfo.shopName }</td><td>${sellerInfo.s_no}</td>
									<td>${sellerInfo.bookName }</td>
									<td>${sellerInfo.number }</td><td>${sellerInfo.amount}</td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-lg-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<center><h3>本月销售额排行</h3></center>
					</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<td>排行</td><td>店铺名</td><td>商家</td><td>图书</td><td>月销售量</td><td>月销售额</td>
							</tr>
							
							<c:set var="i" value="1"></c:set>
							<c:forEach items="${requestScope.monthSaleAmount}" var="sellerInfo">
								<tr>
									<td>${i}</td><td>${sellerInfo.shopName }</td><td>${sellerInfo.s_no}</td>
									<td>${sellerInfo.bookName }</td>
									<td>${sellerInfo.number }</td><td>${sellerInfo.amount}</td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-lg-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<center><h3>本年销售额排行</h3></center>
					</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<td>排行</td><td>店铺名</td><td>商家</td><td>图书</td><td>年销售量</td><td>年销售额</td>
							</tr>
							
							<c:set var="i" value="1"></c:set>
							<c:forEach items="${requestScope.yearSaleAmount}" var="sellerInfo">
								<tr>
									<td>${i}</td><td>${sellerInfo.shopName }</td><td>${sellerInfo.s_no}</td>
									<td>${sellerInfo.bookName }</td>
									<td>${sellerInfo.number }</td><td>${sellerInfo.amount}</td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
