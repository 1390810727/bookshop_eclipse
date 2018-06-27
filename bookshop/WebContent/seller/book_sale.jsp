<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
		<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="/bookshop/js/bootstrap.js" ></script>
		<style>
			.left_nav{
				
				width:100px ;
				position: fixed;
				left: 40px;
				top: 250px;
			}
		</style>
		
		<script type="text/javascript">
			function show(t){
				//$(t).next().show();
				$("#search_sale").animate({right:'0px'});
			}
			function search_shut(){
				$("#search_sale").animate({right:'-500px'});
				
			}
			function searchBySale(){
				var month_input=$("#month_input").val();
				var date_input=$("#date_input").val();
				if(month_input=="" && date_input!="")
					{
					
						location=encodeURI("/bookshop/searchBySale.saleAction?date="+date_input);
					}
				if(month_input!="" && date_input=="")
					{
					
						location=encodeURI("/bookshop/searchBySale.saleAction?date="+month_input);
					}
					$("#search_sale").animate({right:'-500px'});
			}
			
			function searchByNumber(){
				var month_input=$("#month_input").val();
				var date_input=$("#date_input").val();
				if(month_input=="" && date_input!="")
				{
				
					location=encodeURI("/bookshop/searchByNumber.saleAction?date="+date_input);
				}
				if(month_input!="" && date_input=="")
				{
					location=encodeURI("/bookshop/searchByNumber.saleAction?date="+month_input);
				}
				$("#search_sale").animate({right:'-500px'});
			}
		</script>
</head>
<body>
		<% Date date=new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy");
			String year=ft.format(date);
			ft=new SimpleDateFormat("yyyy-MM");
			String month=ft.format(date);
			ft=new SimpleDateFormat("yyyy-MM-dd");
			String day=ft.format(date);
			//System.out.println(year+" "+month+" "+day);
		%>
		<%@include file="nav.jsp" %>
		<%@include file="nav2.jsp" %>
		<div class="panel panel-primary" style="position: fixed; top: 200px; right: -500px; width: 30%;" id="search_sale">
		    <div class="panel-heading" style="position: relative;">
		        <h3 class="panel-title">面板标题</h3>
		        <button style="position:absolute; top:3px ; right: 3px;" class=" btn btn-danger" onclick="search_shut()">关闭</button>
		    </div>
		    <div class="panel-body" style="padding: 50px">
			       	<div class="row">
			       		<input type="month" class="form-control" id="month_input">
			       	</div>
			       	<div class="row">
			       		<input type="date" class="form-control" id="date_input">
			       	</div>
			       	<div class="" style="margin: 30px auto;">
			       		<button class="btn btn-primary" onclick="searchBySale()">销售额查询</button>
			       		<button class="btn btn-primary" onclick="searchByNumber()">销量查询</button>
			       	</div>
		    </div>
		</div>
		<div class="left_nav">
			
			<a href="#" class="list-group-item active" onclick="show(this)" style="border-radius: 40px; text-align: center;">搜索</a>
			<br>
			<a href="/bookshop/searchBySale.saleAction?date=<%=day%>" onclick="test(this)" class="list-group-item">日销售额</a>
			<a href="/bookshop/searchBySale.saleAction?date=<%=month%>" class="list-group-item">月销售额</a>
			<a href="/bookshop/searchBySale.saleAction?date=<%=year%>" class="list-group-item">年销售额</a>
			<a href="/bookshop/searchByNumber.saleAction?date=<%=day%>" class="list-group-item">日销量</a>
			<a href="/bookshop/searchByNumber.saleAction?date=<%=month%>" class="list-group-item">月销量</a>
			<a href="/bookshop/searchByNumber.saleAction?date=<%=year%>" class="list-group-item">年销量</a>
		</div>
		
		<div class="container">
			<table class="table table-hover">
				<tr>
					<td>排行</td><td>图书编号</td><td>图书名称</td>
					<td>
					<c:if test="${requestScope.method=='sale'}">销售额</c:if>
					<c:if test="${requestScope.method=='number'}">销量</c:if>
					
					</td><td>利润</td>
				</tr>
			</table>
			<c:set var="i" value="1"></c:set>
			<c:set var="profit" value='0'></c:set>
			<table class="table table-hover" style="margin: 0px;">
				<c:forEach items="${requestScope.saleList}" var="sale">
					<tr>
						<td>${i}</td><td>${sale.b_no}</td><td>${sale.bookName}</td>
						<td>
						<c:if test="${requestScope.method=='sale'}">${sale.amount }</c:if>
						<c:if test="${requestScope.method=='number'}">${sale.number }</c:if>
						</td>
						<td>${sale.prifit}</td>
					</tr>
					<c:set var="i" value="${i+1}"></c:set>
					<c:set var="profit" value='${profit+sale.prifit}'></c:set>
				</c:forEach>
				<tr>
					<td></td><td></td><td></td><td>总利润</td><th>${profit}元</th>
				
				</tr>
			</table>
			
		
		
		</div>

</body>
</html>