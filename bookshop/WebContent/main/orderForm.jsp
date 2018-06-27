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
<script type="text/javascript" src="/bookshop/main/shopCard.js" ></script>	
<script type="text/javascript">
	function addAddress(){
		$("#addressPad").fadeIn(2000);
		
		
	}

</script>	
</head>
<body>
	<%@ include file="nav.jsp" %>
	
	<%@ include file="nav_right.jsp" %>
	<div class="container">
		   <div class="jumbotron">
		   <c:set var="sameShopBook" value="${requestScope.sameShopBook}"></c:set>
		   <c:set var="addressList" value="${requestScope.addressList}"></c:set>
		        <h1>请付款！</h1>
		        <p>共:<span>${sameShopBook.sumNumber}</span>件图书</p>
		        <p>合计：<span>${sameShopBook.sumPrice}</span>元</p>
		        <p>
		        		 <label for="name">请选择收货地址</label>
		        		 
		        		 <label style="margin-left:40px; position:relative;">
		        		 	<a class="btn btn-default" onclick="addAddress();">
		        		 		添加
		        		 	</a>	
		        		 	
			        		 	 <div class="panel panel-default" style="width: 40%; display: none; posistion: absolute"
			        		 	  id="addressPad">
								    <div class="panel-heading">
								        <h3 class="panel-title">
								            请填写收货地址信息
								        </h3>
								    </div>
								    <div class="panel-body">
								    	<form action="/bookshop/addAddress.c_orderFormAction" method="post">
									       	 <p>姓名:<input type="text" name="name" class="form-control"></p>
									       	 <p>省:<input type="text" name="province" class="form-control"></p>
									       	 <p>市:<input type="text" name="city" class="form-control"></p>
									       	 <p>县:<input type="text" name="country" class="form-control"></p>
									       	 <p>街道:<input type="text" name="street" class="form-control"></p>
									       	 <p>邮政编码:<input type="text" name="post_code" class="form-control"></p>
									       	 <p>手机号:<input type="text" name="tell_no" class="form-control"></p>
									       	 <input type="hidden" name="s_no" value="${sameShopBook.s_no}" >
									       	 <p><input type="submit" class="btn btn-primary" value="添加"></p>
							       	 	</form>
								    </div>
								</div>
	        		 	</label>
        		 </p>
		        <form action="/bookshop/payAction.c_orderFormAction" method="post">
		        	<p>
						    <select class="form-control" name="address">
					    	  
					    	  <c:forEach items="${addressList}" var="address">
							     <option value="${address.a_no}">
							  	 ${address.name} ${address.tell_no} 
								${address.province}${address.city}${address.country}${address.street} 
  								${address.post_code}
						      	</option>
						      </c:forEach>
						     
						    </select>
		        	</p>
		        	<p>
		        		<label for="name">请选择付款方式</label>
						<div>
						  
						    <label class="radio-inline">
						        <input type="radio" name="payMethod" id="optionsRadios3" value="微信" checked> 微信
						    </label>
						    <label class="radio-inline">
						        <input type="radio" name="payMethod" id="optionsRadios4"  value="支付宝"> 支付宝
						    </label>
						    <label class="radio-inline">
						        <input type="radio" name="payMethod" id="optionsRadios4"  value="银行卡"> 银行卡
						    </label>
						</div>
		        	</p>
		        	<p>
		        		<input type="hidden" name="s_no"  value="${sameShopBook.s_no}" />
		        		<input type="submit" class="btn btn-primary btn-lg" value="确认" />
		        	</p>
		        </form>
		      
		   </div>
		</div>

	<%@ include file="c_login.jsp" %>
</body>
</html>