<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<script src="../js/bootstrap.min.js"></script>
		<script src="../js/jquery-3.2.1.js"></script>
		

		<!--<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
		<!--<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">-->
			
		<style>
			div{
				/*border: solid 1px;*/
			}
			.container{
				text-align: center;
				width: 100%;
				position: fixed;
				top: 200px;
				opacity:0.9;
  				filter:alpha(opacity=40);
				
			}
			.login{
				
				margin: 20px auto;
				width: 400px;
				
			}
			body{
				/*background-image:url(../img/2.jpg);
				background-repeat: no-repeat;*/
			}
			img{
				height: 100%;
				width: 100%;
				border: 0pxs;
			}
			.blur 
			{
				
				   opacity:6;
    				filter:alpha(opacity=100);
    				/*filter: opacity(70%);*/
    				/*-webkit-filter: opacity(160%);*/
				}
			
			
		</style>
		<script type="text/javascript">
			window.onkeydown=function(e){
				
				//alert(e.keyCode);
				//如果按下的是回车，则相当与按下登陆按钮
				if(e.keyCode==13){
					$("#login").click();
				}
			}
			
			//当页面加载完时执行
			$(function(){
				$("#login").bind("click",check_ajax);
			})
			
			//检测输入的id是否合理
			function check_id()
			{
				//商家的id不超过十个字符
				var id_input=$("#id").val();
				var reg =/^[0-9a-zA-Z]+$/;
				if(reg.test(id_input) && id_input.length<=10){
					
						return true;
				}else{
				
					$("#message").html("账号格式错误");
					$("#message").css("color","red");
					return false;
				}
			}
			
			//检测商家的姓名,只检测长度,不得超过10个字符
			function check_password(){
				var password=$("#password").val();
				if(password.length<=10 && password.length>0)
					{
						
						return true;
					}else{
						
						$("#message").html("密码格式错误");
						$("#message").css("color","red");
						return false;	
					}
			}
			//检测验证码是否输入
			
			function check_imgCode(){
				var imgCode=$("#imgCode").val();
				if(imgCode.length==0)
					{
						$("#message").html("请输入验证码");
						$("#message").css("color","red");
						return false;
					}
					return true;
			}
			
			function check_ajax()
			{
				var xmlhttp;
				if(window.XMLHttpRequest)
				{
					xmlhttp= new XMLHttpRequest();
				}else{
					xmlhttp= ActiveXObject("Mircosoft.XMLHTTP");
				}
				
				var message;
				xmlhttp.open("post","check_admin.adminLogin",true);
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState==4 && xmlhttp.status==200)
						{
							message=xmlhttp.responseText;
							if(message==1){
								document.getElementById("myform").submit();
								return;
							}
							$("#message").html(message);
							$("#message").css("color","red");
					}
				}
				xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				var id=$("#id").val();
				var password=$("#password").val();
				var code=$("#imgCode").val();
				xmlhttp.send("id="+id+"&password="+password+"&imgCode="+code);
			}
			
			
		
		</script>
	</head>
	<body>
		
		<img src="../img/login.jpg" class="blur" />
		<div class="container">
			
		 <div class="login">
		 	
		 	
			<div class="panel panel-default">
			    <div class="panel-heading">
			        <h3 class="panel-title">
			            bookshop
			        </h3>
			    </div>
			    <div class="panel-body">
			    	<!--form表单-->
			    	<form class="form-horizontal" id="myform" action="/bookshop/loginAdmin.adminLogin" role="form" >
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">账号</label>
							<div class="col-sm-8">
							<input type="text" class="form-control" id="id" name="id"
									   placeholder="请输入账号">
							</div>
							<div class="col-sm-1">
								<!-- <span class="glyphicon glyphicon-remove " style="color: red"></span> -->
								
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-8">
								<input type="password" class="form-control" id="password" name="password"
									   placeholder="请输入密码">
							</div>
							<div class="col-sm-1">
								<span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-3 control-label">验证码</label>
							<div class="col-sm-8" align="left">
								<input type="text" name="imgCode" id="imgCode" class="form-control" placehodler="请输入验证码"/><img src="/bookshop/createImgCode" style="height: 34px; width: 100px" alt="验证码" onclick="this.src='/bookshop/createImgCode?'+Math.random()" />
								<span id="message"></span>
							</div>
							<div class="col-sm-1"></div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label>
										<input type="checkbox"> 请记住我
									</label>
									<button type="button" class="btn btn-default" id="login">登录</button>
										<a><button type="button" class="btn btn-default">注册</button></a>
									<a>忘记密码？</a>
								</div>
							
							</div>
						</div>
					</form>
			    </div>
			</div>
		 </div>
		</div>
	</body>
</html>
