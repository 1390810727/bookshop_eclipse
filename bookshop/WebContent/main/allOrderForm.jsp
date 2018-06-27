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
		<script type="text/javascript" src="/bookshop/main/shopCard.js" ></script>
		<script>
		//对指定店铺的订单 付款
			function returnGoods(createDate,status,s_no){
				var form=document.createElement("form");
				var input_cd=document.createElement("input");
				input_cd.name="createDate";
				input_cd.value=createDate;
				
				var input_status=document.createElement("input");
				input_status.name="status";
				input_status.value=status;
				
				var input_s_no=document.createElement("input");
				input_s_no.name="s_no";
				input_s_no.value=s_no;
				
				form.appendChild(input_cd);
				form.appendChild(input_status);
				form.appendChild(input_s_no);
				
				form.action="/bookshop/returnGoods.c_orderFormAction";
				form.submit(); 	
			
			}
		
		function showLogistic(createDate){
			var form=document.createElement("form");
			var input_cd=document.createElement("input");
			input_cd.name="createDate";
			input_cd.value=createDate;
			
			form.appendChild(input_cd);
			
			form.action="/bookshop/showLogistic.c_orderFormAction";
			form.submit(); 
		}
		function pay(s_no){
			window.location="/bookshop/payOrderForm.c_orderFormAction?s_no="+s_no;
		}
		
		</script>
	</head>
	<body>
				<%@ include file="nav.jsp" %>
				<%@ include file="lunbo.jsp" %>
		<div class="container">
		
				<%@ include file="nav_orderForm.jsp" %> 
			
		   <div class="jumbotron">
		      <table class="table">
				  <caption>基本的表格布局</caption>
				  <thead>
				    <tr>
				      <th width="40%">宝贝</th><th width="10%">单价</th><th width="10%">数量</th><th width="20%">需付款</th><th width="10%">交易状态</th><th width="10%">交易操作</th>
				    </tr>
				  </thead>
				  <tbody style="padding: 0px;">
				  	<c:forEach  items="${requestScope.sameShopBookList}" var="sameShopBook">
				  	
				  	
				  	<table class="table">
				  		<thead>
					  		<tr>
					  			<th width="40%">${sameShopBook.shopName}</th><th> </th><th width="10%"> </th><th width="20%"> </th><th width="10%"> </th><th width="10%"> </th>
					  		</tr>
				  		</thead>
				  		<!--店铺下的图书循环点  -->
				  		<c:set var="orderFormInfoList" value="${sameShopBook.orderFormInfoList}"></c:set>
				  		<c:forEach items="${orderFormInfoList}" var="orderFormInfo">
				  		<c:set var="orderForm" value="${orderFormInfo.orderForm}"></c:set>
						    <tr>
		   				      <td>
						      	<div class="col-lg-4">
						      		<img src="${orderFormInfo.imgAddress}" style="width: 100%;">
						      	</div>
						      	<div class="col-lg-8 o_bookInfo">
						      		<p style="font-size: small;">书名：<span>${orderFormInfo.bookName}</span></p>
						      		<p style="font-size: small;">编号：<span>${orderFormInfo.orderForm.b_no}</span></p>
						      		<p style="font-size: small;">作者：<span>${orderFormInfo.author}</span></p>
						      		<p style="font-size: small;">出版社：<span>${orderFormInfo.publish}</span></p>
						      	</div>
						      </td>
						      
						      <td>${orderForm.price}</td><td>${orderForm.number}</td><td>${orderForm.price*orderForm.number}</td><td>${orderForm.status}</td><td> </td>
						    </tr>
				  		</c:forEach>
						<!--店铺下的图书循环点  -->
						
						 <c:choose>
						    <c:when test="${orderForm.status == '待付款'}">
							    <tr>
							    	<td></td><td></td><td>共<span>${sameShopBook.sumNumber}</span>件</td><td>合计：<span>${sameShopBook.sumPrice}</span><span>元</span></td><td></td>
							    	<td><button class="btn btn-danger" onclick="pay(${sameShopBook.s_no});">付款</button></td>
							    </tr>
						    </c:when>
						    
						    <c:when test='${orderForm.status=="待发货"}'>
						    	<tr>
							    	<td></td><td></td><td>共<span>${sameShopBook.sumNumber}</span>件</td><td>合计：<span>${sameShopBook.sumPrice}</span><span>元</span></td>
							    	<td><button class="btn btn-danger" onclick="remindDelivery()">提醒发货</button></td>
							    	<td><button class="btn btn-danger" onclick="returnGoods(${orderForm.createDate},${orderForm.status},${orderForm.s_no})">退货</button></td>
							    </tr>
						    </c:when>
						    <c:when test="${orderForm.status=='待收货'}">
						    	<tr>
							    	<td></td><td>共<span>${sameShopBook.sumNumber}</span>件</td><td>合计：<span>${sameShopBook.sumPrice}</span><span>元</span></td>
							    	<td><button class="btn btn-danger" onclick="showLogistics(${orderForm.createDate})">查看物流</button></td>
							    	<td><button class="btn btn-danger" onclick="">确认收货</button></td>
							    	<td><button class="btn btn-danger" onclick="" >退货</button></td>
							    </tr>
						    </c:when>
						    <c:when test="${orderForm.status=='待评价'}">
						    	<tr>
							    	<td></td><td></td><td>共<span>${sameShopBook.sumNumber}</span>件</td><td>合计：<span>${sameShopBook.sumPrice}</span><span>元</span></td><td></td>
							    	<td><button class="btn btn-danger" onclick="">评价</button></td>
							    </tr>
						    </c:when>
						    <c:otherwise>
						    </c:otherwise>
					    </c:choose>
				  	</table>
				  	<!-- 店铺循环点 -->
				  	</c:forEach>
				  </tbody>
				</table>
		   </div>
		</div>
		
	<%@ include file="nav_right.jsp" %>
	</body>
</html>
