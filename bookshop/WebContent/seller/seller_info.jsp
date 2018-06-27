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
	<script type="text/javascript">
		$(function(){
			var message=$("#deleteMessage").val();
			if(message=="删除成功" || message=="删除失败")
				{
					alert(message);
				}
		});
	
		function deleteShop(t)
		{
			var content=prompt("确定要注销吗，注销之后不可撤销，您的店铺信息将全都不再，如果确定要注销请输入‘是’","否");
			if (content=="是")
			{
				var s_no=$(t).parent().prev().prev().html();
				location="/bookshop/deleteShop.sellerAction?s_no="+s_no;
			    
			}
		}
		
		function intoShop(t){
			var s_no=$(t).parent().prev().prev().html();
			var shopName=$(t).parent().prev().html();
			location="/bookshop/chooseShop.shopAction?s_no"+s_no+"&shopName="+shopName;
			
		}
	
	</script>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<!--用来存储request请求来的消息  -->
	<input type="hidden" id="deleteMessage" value="${requestScope.deleteMessage}" />
	
	<div class="container">
		<div class="col-lg-6 col-sm-6 col-xs-12">
			<div class="panel panel-default">
			    <div class="panel-heading">
			        <span class="panel-title">
			            		商家信息
			        </span>
			        <a href="/bookshop/loadShopInfo.shopAction" style="float: right; margin-right: 30px">修改密码</a>
			        <a href="/bookshop/loadShopInfo.shopAction" style="float: right; margin-right: 30px">修改基本信息</a>
			    </div>
			    <div class="panel-body">
			    <ul class="list-group">
			    	
				    <li class="list-group-item"><strong>账号:</strong>${seller.id }</li>
				    <li class="list-group-item"><strong>姓名:</strong>${seller.name }</li>
				    <li class="list-group-item"><strong>性别：</strong>${seller.gender }</li>
				    <li class="list-group-item"><strong>电话:</strong>${seller.tel }</li>
				    
				</ul>
				
			    </div>
			</div>
		</div>
		<div class="col-lg-6 col-sm-6 col-xs-12">
			<div class="panel panel-default">
			    <div class="panel-heading">
			        <span class="panel-title">
			            		所拥有的店铺
			        </span>
<!-- 			      <a href="/bookshop/loadShopInfo.shopAction" style="float: right; margin-right: 30px">修改</a> -->
			    </div>
			    <div class="panel-body">
				    <table class="table">
				    	<tr>
				    		<td>店铺号</td><td>店铺名</td><td>操作</td>
				    	</tr>
				    <!-- 从request里获取改商家所拥有的店铺，商家可以对店铺进行删除操作 -->
				    	 <c:forEach items="${requestScope.shopList}" var="shop"> 
						   <tr>
						   	<td>${shop.s_no }</td><td>${shop.shopName }</td><td><a href="#"  onclick="deleteShop(this)">注销</a> <a href="#" onclick="intoShop(this)" style="float: right;">进入</a></td>
						   </tr>
			    		 </c:forEach>	
					    
				    </table>
				
			    </div>
			</div>
		</div>
	</div>
</body>
</html>