<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	function sale(t){
		var date=new Date();
		var today=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
		location=encodeURI("/bookshop/searchBySale.saleAction?date="+today);
	}
</script>
<div style="padding:5px 50px;">
			<div class="container">
				<form id="search" action="/bookshop/searchBook.bookAction" method="get">
					<center><h4>${sessionScope.shopName }</h4></center>
					<ul class="nav nav-tabs" id="nav">
					  <li><a href="/bookshop/getShopInfo.shopAction">店铺信息</a></li>
					  <li><a onclick="sale(this)">销量信息</a></li>
					  <li><a href="/bookshop/seller/book_storage.jsp">图书入库</a></li>
					  <li><a href="/bookshop/bookList.bookAction">图书管理</a></li>
					  <li><a href="/bookshop/orderFormList.orderFormAction?status=待付款">订单管理</a></li>
					  <li><a href="/bookshop/commentList.commentAction">顾客评论</a></li>
					  <li style="float: right;"><a href="#" onclick="javaScript:$('#search').submit();" type="submit">查询</a></li>
					  <li style="float: right; ">
							<input type="text" name="searchContent" class="form-control"/>
					  </li>
					</ul>
				</form>
			</div>
		</div>