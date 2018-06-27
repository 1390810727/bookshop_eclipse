
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
		<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="/bookshop/js/bootstrap.min.js" ></script>
		<style>
			div{
				/*border:solid 1px;*/
			}
			.container{
				text-align: center;
				
			}
			#alignCenter{
				width: 500px;
				margin: 20px auto;
			}
		</style>
		<script type="text/javascript">
			/*  表单提交前的数据检验*/
			
			//当页面加载完时执行
			$(function(){
				//后绑定事件,商家id绑定blur事件
				$("#id").blur(check_id);
				//给商家name绑定blur事件;
				$("#name").bind("blur",check_name);
				//给密码password绑定blur事件
				$("#password1").bind("blur",check_password1);
				$("#password2").bind("blur",check_password2);
				//给电话tel绑定blur事件
				$("#tel").bind("blur",check_tel);
				
				
				
			})
			
			//检测输入的id是否合理
			function check_id()
			{
				//商家的id不超过十个字符
				var id_input=$("#id").val();
				var reg =/^[0-9a-zA-Z]+$/;
				var id=$("#id").val();
				var message;
				if(reg.test(id_input) && id_input.length<=10){
						$.ajax({
							url: "/bookshop/seller_ajax.register",
							data: "id="+id,
							type: "post",
							dataType: "text",
							success: function(result){
								$("#id_message").html(result);
								$("#id_message").css("color","green");
								return true;
							}
						});
				}else{
					
					$("#id_message").html("格式错误");
					$("#id_message").css("color","red");
					return false;
				}
			}
			
			//检测商家的姓名,只检测长度,不得超过10个字符
			function check_name(){
				var name_input=$("#name").val();
				if(name_input.length<=10 && name_input.length>0)
					{
						$("#name_message").html("格式正确");
						$("#name_message").css("color","green");
						return true;
					}else{
						$("#name_message").html("格式错误");
						$("#name_message").css("color","red");
						return false;	
					}
			}
			
			//检测用户的密码最少6个，最大10个字母或数字
			function check_password1()
			{
				var password_input=$("#password1").val();
				var reg=/^[0-9a-zA-Z]+$/;
				if(reg.test(password_input) && password_input.length>=6 && password_input.length<=10)
					{
						$("#password1_message").html("格式正确");
						$("#password1_message").css("color","green");
						return true;
					}else{
						$("#password1_message").html("格式错误");
						$("#password1_message").css("color","red");
						return false;
					}
			}
			
			//检测再次确认的密码是否与第一个密码一样，如果不一样则提示输入第一个密码
			function check_password2(){
				var password1_input=$("#password1").val();

				var password2_input=$("#password2").val();
				//1.先比较密码1是否合格且已经输入
				if(!check_password1())
					{
					$("#password1_message").html("请先输入此密码");
					$("#password1_message").css("color","red");
					return false;
					}
				//比较两个密码是否相同
				if(password2_input == password1_input)
					{
						$("#password2_message").html("密码相符");
						$("#password2_message").css("color","green");
						return true;
					}else{
						$("#password2_message").html("密码错误");
						$("#password2_message").css("color","red");
						return false;
						
					}
			}
			
			//检测电话的格式，电话的长度为11个数字
			function check_tel(){
				var reg=/^[0-9]+$/;
				var tel_input=$("#tel").val();
				if(reg.test(tel_input) && tel_input.length==11)
					{
						$("#tel_message").html("格式正确");
						$("#tel_message").css("color","green");
						return true;
					}else{
						
						$("#tel_message").html("格式错误");
						$("#tel_message").css("color","red");
						return false;
					}
			}
	
	
		
			//表单提交的触发事件
			function check_submit(){
				//获取的内容字符串有空格，所以必须加trim()方法
				var isOk=$("#id_message").text().trim()=="账号可使用";
				
				if(isOk + check_name() + check_password1() + check_password2() + check_tel()==5)
					{
						
						return true;
					}else {
						
						return false;
					}
				
			}
		
		</script>
	</head>
	<body>
		<!--
        	作者：袁奎
        	时间：2017-11-13
        	描述：商家店铺注册页面
        -->
		<center><h1>商家注册界面</h1></center>
		<p>
			商家注册之后如果该商家没有店铺，则让他注册店铺，如果有了的话，则显示店铺列表（可拥有多个店铺）
		</p>
		
		<div class="container">
			
			
		<div  id="alignCenter">
			<form class="form-horizontal" action="/bookshop/registerSeller.register" method="post" onsubmit="return check_submit()">	
				  <div class="form-group">
				    <label class="col-sm-2 control-label">商家id</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" id="id" name="id" placeholder="请输入数字或字母，不超过10个">
				    </div>
				    <div class="col-sm-3">
				    	<span id="id_message"></span>
				    </div>
				  </div>
				  
				  <div class="form-group">
					    <label class="col-sm-2 control-label">姓名</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
					    </div>
					     <div class="col-sm-3">
				    	<span id="name_message"></span>
				    </div>
				  </div>
				  <div>
				  <div class="form-group">
					    <label class="col-sm-2 control-label">密码</label>
					    <div class="col-sm-7">
					      <input type="password" class="form-control" id="password1" name="password" placeholder="请输入密码">
					    </div>
					     <div class="col-sm-3">
				    	<span id="password1_message"></span>
				    </div>
				  </div>
				  <div>
				   <div class="form-group">
					    <label class="col-sm-2 control-label">密码</label>
					    <div class="col-sm-7">
					      <input type="password" class="form-control" id="password2" placeholder="请再次输入密码">
					    </div>
					     <div class="col-sm-3">
				    	<span id="password2_message"></span>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label class="col-sm-2 control-label">性别</label>
				  	<div class="col-sm-7">
						    <label>
						        <input type="radio" name="gender" id="gender1" value="男" checked="true">男
						    </label>
						    <label>
						        <input type="radio" name="gender" id="gender2" value="女">女
						    </label>
					 </div>
					  <div class="col-sm-3">
				    	<span></span>
				    </div>
				  </div>

				 
				    
				  	<div class="form-group">
					    <label class="col-sm-2 control-label">电话</label>
					    <div class="col-sm-7">
					      <input type="tel" class="form-control" id="tel" name="tel" placeholder="请输入您的联系方式">
					    </div>
					     <div class="col-sm-3">
				    	<span id="tel_message"></span>
				    </div>
				  	</div>
				  

	

		
				 
		
				  
				  <div class="form-group">
				    <div class=" col-sm-10">
				      <button type="submit" class="btn btn-default">注册</button>
				    </div>
				  </div>
			</form>
		 </div>
		</div>
	</body>
</html>
	
</body>
</html>