<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		
		<script type="text/javascript" src="../js/jquery-3.2.1.js" ></script>
		<script src="../js/bootstrap.min.js"></script>
	
		
		<style>
			div{
				
				/*border: solid 1px;*/
			}
			.container div{
				height: 400px;
			}
			.icon-panel{
				
				text-align: center;
				align-content: center;
			}
			.icon-panel center{
				
				font-size: large;
			}
		</style>
	</head>
	<body>
		<%@ include file="nav.jsp" %>
		<div class="container">
		   <div class="jumbotron">
		        <h1>系统管理员的界面！其权限为:</h1>
		        <p>查看商家的信息（月销量、日销量、年销量、月销售额、日销售额、年销售额、销售排行等）</p>
		        <p>管理自营的商品</p>
		        <p>自营的商品入库</p>
		      </p>
		   </div>
		   <div class="col-xs-12 col-sm-4 icon-panel">
							<button type="button" class="btn btn-primary btn-lg" onclick="window.location='/bookshop/sellerInfo.sellerInfoAction'"  style="font-size: 60px ;height: 200px;width: 200px;">
							  <span class="glyphicon  glyphicon-calendar"></span>
							</button>	
							<center>商家信息</center>
							<p class="text-muted">查看商家的月销售、日销售、年销售、以及排行榜，根据商家的销售额来收取相应的税</p>
		   </div>
		   <div class="col-xs-12 col-sm-4 icon-panel">
				       <button type="button" class="btn btn-primary btn-lg" style="font-size: 60px ;height: 200px; width:200px;">
						  <span class="glyphicon  glyphicon-briefcase"></span>
						</button>
						<center>自营图书</center>
						<p class="text-muted">管理本网站自营的图书，我的网站我做主</p>
		   </div>
		   <div class="col-xs-12 col-sm-4 icon-panel">
				        	<button type="button" class="btn btn-primary btn-lg" 
				        		 style="font-size: 60px ;height: 200px; width: 200px;">
							  <span class="glyphicon  glyphicon-camera"></span>
							</button>
							<center>反馈</center>
							<p class="text-muted">接受用户或商家的反馈情况，根据用户的反馈及时做到修正，让本网站越来越优秀</p>
		   </div>
		</div>
	</body>
</html>
