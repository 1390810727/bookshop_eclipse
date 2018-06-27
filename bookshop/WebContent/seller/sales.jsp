<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<script type="text/javascript" src="../js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="../js/bootstrap.js" ></script>
		<style>
			.container .row >div{
				
				
				border: solid 1px blue;
				height: 300px;
			}
			.container p{
				
				
			}
			
			.container img{
				
				height: 60%;
				width: 100%;
				margin: 10px auto;
			}
			.left_nav{
				
				width:100px ;
				position: fixed;
				left: 40px;
				top: 250px;
				
			}
			
		</style>
	</head>
	<body>
		<%@include file="nav.jsp" %>
		<%@include file="nav2.jsp" %>
		
		<!--显示主要的内容-->
		<div class="left_nav">
			<a href="#" class="list-group-item">日销量</a>
			<a href="#" class="list-group-item">月销量</a>
			<a href="#" class="list-group-item">年销量</a>
			<a href="#" class="list-group-item">日销售额</a>
			<a href="#" class="list-group-item">月销售额</a>
			<a href="#" class="list-group-item">年销售额</a>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg" class="img-rounded" />
					<p>
						编号：10000
					</p>
					<p>
						书名：计算即组成原理
					</p>
					<p>
						销量: 40
					</p>
				</div>
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg" class="img-rounded" />
					<p>
						编号：10000
					</p>
					<p>
						书名：计算即组成原理
					</p>
					<p>
						销量: 40
					</p>
				</div>
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg" class="img-rounded" />
				</div>
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg" class="img-rounded" />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg" class="img-rounded" />
				</div>
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg" class="img-rounded" />
				</div>
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg"  class="img-rounded"/>
				</div>
				<div class="col-sm-6 col-lg-3" style="background-color: green;">
					<img src="../img/3.jpg" class="img-rounded"/>
				</div>
			</div>
			
			
		</div>
	</body>
</html>
