<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	
	<nav class="navbar navbar-default" role="navigation" style="height: ;">
		<div class="container-fluid"> 
		    <div class="navbar-header">
		        <a class="navbar-brand" href="/bookshop/getShopList.shopAction">商家页面</a>
		    </div>
		    <div>
		        <p class="navbar-text">您好！${sessionScope.seller_name }！</p>
		    </div>
		    <div class="navbar-form navbar-right" style="width:300px">
		    	<div class="col-sm-4">
		    		<a href="/bookshop/getSellerInfo.sellerAction">个人</a>
		    	</div>
		    	<div class="col-sm-4">
						<a>消息</a><span style="border: solid 1px; border-radius: 50% 50%; background-color: green;" >4</span>
		    	</div>
		    	<div class="col-sm-4">
		    		<a href="/bookshop/seller_logout.logout">退出</a>
		    	</div>
		    	
		    </div>
		</div>
	</nav>