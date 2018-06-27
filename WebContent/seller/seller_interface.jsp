<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			}
			
			.container>div{
				
				
			}
			
			.content{
				height: 300px;
				
			}
			.pagination{
				width: 100%;
				text-align: center;
			}
			
			
		</style>
	</head>
	<body>
		<%@ include file="nav.jsp" %>
		<%@ include file="nav2.jsp" %>
		
		
		<div class="container">
			<div class="col-sm-8">
				
				<div class="content">
					<div class="panel panel-default">
					    <div class="panel-heading">
					        <h3 class="panel-title">
					            这里主要显示今日销量的情况
					        </h3>
					    </div>
					    <div class="panel-body">
					       	<ul class="list-group">
								<li class="list-group-item">免费域名注册</li>
								<li class="list-group-item">免费 Window 空间托管</li>
								<li class="list-group-item">图像的数量</li>
								<li class="list-group-item">24*7 支持</li>
								<li class="list-group-item">每年更新成本</li>
							</ul>
					    </div>
					</div>
				</div>
				<div class="pagination">
					
					<ul class="pagination">
					    <li><a href="#">&laquo;</a></li>
					    <li class="active"><a href="#">1</a></li>
					    <li class="disabled"><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">5</a></li>
					    <li><a href="#">&raquo;</a></li>
					</ul>
					
				</div>
			</div>
			<div class="col-sm-4">
				
				
				
				<div class="panel panel-default">
				    <div class="panel-heading">
				        <h3 class="panel-title">
				            	购买信息
				        </h3>
				    </div>
				    <div class="panel-body">
				        <ul class="list-group">
							<li class="list-group-item">免费域名注册</li>
							<li class="list-group-item">免费 Window 空间托管</li>
							<li class="list-group-item">图像的数量</li>
							<li class="list-group-item">24*7 支持</li>
							<li class="list-group-item">每年更新成本</li>
						</ul>
				    </div>
				</div>
				<div class="panel panel-default">
				    <div class="panel-heading">
				        <h3 class="panel-title">
				            评论信息
				        </h3>
				    </div>
				    <div class="panel-body">
				         <ul class="list-group">
							<li class="list-group-item">免费域名注册</li>
							<li class="list-group-item">免费 Window 空间托管</li>
							<li class="list-group-item">图像的数量</li>
							<li class="list-group-item">24*7 支持</li>
							<li class="list-group-item">每年更新成本</li>
						</ul>
				    </div>
				</div>
			</div>
			
		</div>
		
		
		
	</body>
</html>
