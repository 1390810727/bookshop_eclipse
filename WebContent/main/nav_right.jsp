<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
	.nav_right{
		position: fixed;
		right: 0px;
		top:30%;
	}
</style>
<%@ include file="shop_card.jsp" %>
<div class="nav_right">
	<a href="/bookshop/mainPage" class="list-group-item"><span class="glyphicon glyphicon-home"></span> 主页</a>
	<a href="#" id="shop_card" class="list-group-item"><span class="glyphicon glyphicon-shopping-cart"></span> 购物车</a>
	<a href="/bookshop/main/paying.c_orderFormAction" class="list-group-item"><span class="glyphicon glyphicon-list"></span> 我的订单</a>
	<a href="#" class="list-group-item"><span class="glyphicon glyphicon-user"></span> 个人</a>
	<a href="javascript:show()" class="list-group-item"><span class="glyphicon glyphicon-circle-arrow-right"></span> 登陆</a>
</div>
<%@ include file="c_login.jsp" %>
