<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户反馈页面</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<script src="../js/jquery.js"></script>
		<script src="../js/bootstrap.js"></script>
		<style>
			div{
				
				/*border: solid 1px;*/
			}
			body{
				background-image: url(../img/feedback.jpg);
				background-repeat: no-repeat;
				
				background-size: cover;
				border: 1px solid;
				
			}
			.container{
				position: relative;
				width: 100%;
				text-align: center;
				padding: 20px 40px;
				
				border: solid 2px red;
			}
			.content{
				margin: 30px auto;
				
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="content">
				<center><h1 style="color: white;">反馈我们</h1></center>
				<center><p style="color: white;">你的反馈将会使我们的网站更加完美</p></center>
				<br><br><br><br><br><br>
				<div class="col-xs-12 col-sm-6 col-lg-6">
					<div class="col-sm-6">
						<input type="text" class="form-control"  placeholder="请输入姓名"/>
					</div>
					
					<div class="col-sm-6">
						<input type="text" class="form-control" placeholder="请留下联系电话"/>
						
					</div>
					<br><br><br>
					<div class="col-sm-12 col-lg-12">
						
							<input type="text" class="form-control" placeholder="请输入反馈主题" />
							
						
						
					</div>
					<br><br><br>
					<div class="col-sm-12 col-lg-12">
						<textarea class="form-control"  rows="6" placeholder="请详细说明反馈问题"></textarea>						
						
					</div>
					
					
						<button type="submit" class="btn btn-default" >提交</button>
					
				</div>
				<div class="col-xs-12 col-sm-6 col-lg-6" style="color: white; text-align: left;">
					<center><h3></h3></center>
					<p>如果有更多的问题，或者更严重的问题请及时给我们反馈。您也可以按如下方式直接联系我们</p>
					<p ><span class="glyphicon glyphicon-tent"></span>电话：15639738590 </p>
					<p>qq:528227496</p>
					<p>email:528227496@qq.com</p>
				</div>
			</div>
		</div>
	</body>
</html>
