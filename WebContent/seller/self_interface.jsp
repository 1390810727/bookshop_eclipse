<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
		<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="/bookshop/js/bootstrap.js" ></script>
		<style>
			div{
					border: solid 0px;
					border-radius: 10px 10px;
				}
			
			#content{
				height: 620px;
				margin: 20px auto;
				background-color: #F2DEDE;
				padding-top: 20px;
			}
				
			#content>div{
				
				height: 300px;
			}
			
			.icon-panel{
				
				text-align: center;
				align-content: center;
			}
			.icon-panel center{
				
				font-size: large;
			}
		</style>
		
		<script type="text/javascript">
		
			
			function choose_shop(t)
			{
				
				var shop=$(t).parent().find("center");
			
				var shopName=shop.get(0).innerText;
				var s_no=shop.get(1).innerText;
				//因为地址中传的有中文，在ie中不能通过，必须对地址统一编码，encodeURI()方法将地址进行utf-8编码
				location=encodeURI("/bookshop/chooseShop.shopAction?shopName="+shopName+"&s_no="+s_no);
			}
		
		</script>
		
	</head>
	<body>
		<%@ include file="nav.jsp" %>
					
					<div class="container">
						<p class="text-center"><h3>请选择您的店铺</h3></p>
						<ul class="nav nav-pills navbar-right" >
						  <li class="active"><a href="/bookshop/seller/shopRegister.jsp">注册店铺</a></li>
						  <li class="active"><a href="#">注销店铺</a></li>
						</ul>
					</div>
					
					<div class="container" id="content">
					
						<c:forEach items="${requestScope.shopList }" var="shop">
						
							<div class="col-xs-12 col-sm-6 col-lg-4 icon-panel">
								<button type="button" class="btn btn-primary btn-lg" onclick="choose_shop(this);"  style="font-size: 60px ;height: 200px;width: 200px;">
								  <span class="glyphicon  glyphicon-calendar"></span>
								</button>	
								<center name="shopName">${shop.shopName }</center>
								<center name="s_no">${shop.s_no }</center>
								<p class="text-muted">
									${shop.introduction }
								</p>
			   				</div>
						</c:forEach>						
					</div>
					
		
	</body>
</html>
