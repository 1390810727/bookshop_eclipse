<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
	<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="/bookshop/js/bootstrap.js" ></script>
	<script type="text/javascript">
		window.onload=function()
		{
			var message=$("#updateMessage").val();
			if(message=="修改成功" || message=="修改失败")
			{
				alert(message);
			}
		}
	</script>
</head>
<body>
		<%@ include file="nav.jsp" %>
		<%@ include file="nav2.jsp" %>
		<input type="hidden" id="updateMessage" value="${requestScope.updateMessage}" />
		<div class="container">
			<div class="panel panel-default">
			    <div class="panel-heading">
			        <span class="panel-title">
			            		店铺信息
			        </span>
			        <a href="/bookshop/loadShopInfo.shopAction" style="float: right; margin-right: 30px">修改</a>
			    </div>
			    <div class="panel-body">
			    <ul class="list-group">
			    	
				    <li class="list-group-item"><strong>店铺封面:</strong><img src=${shop.imgAddress} width="400px" height="250px"></li>
				    <li class="list-group-item"><strong>店铺号:</strong> ${shop.s_no }</li>
				    <li class="list-group-item"><strong>店铺名：</strong>${shop.shopName }</li>
				    <li class="list-group-item"><strong>所在地址:</strong>${shop.province } 省   ${shop.city }  市   ${shop.country }  县    ${shop.street }</li>
				    <li class="list-group-item"><strong>邮编：</strong>${shop.postCode }</li>
				    <li class="list-group-item"><strong>店铺描述：</strong>${shop.introduction }</li>
				    <li class="list-group-item"><strong>店主：</strong>${shop.s_id }</li>
				</ul>
				
			    </div>
			</div>
		
		</div>
</body>
</html>