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
	<script type="text/javascript" src="/bookshop/js/address_change.js"></script>
	
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
		$(function(){
				$("#province").bind("change",provinceChange);
				$("#city").bind("change",cityChange);
				
				
			})
			/*  表单提交前的数据检验*/
			
			//当页面加载完时执行
			$(function(){
				//后绑定事件,商家id绑定blur事件
				$("#id").blur(check_id);
				//给店铺名称shopName绑定blur事件
				$("#shopName").bind("blur",check_shopName);
				
				$("#province").blur(check_province);
				
				//绑定市的blur事件
				$("#city").blur(check_city);
				
				//绑定市的blur事件
				$("#country").blur(check_country);
				
				//给街道绑定blur事件
				$("#street").blur(check_street);
				
				//给邮编绑定blur事件
				$("#code").blur(check_code);
				//给店铺简介introduction 绑定blur事件
				$("#introduction").blur(check_intro);
			})
			
			//检测输入的id是否合理
			function check_id()
			{
				//商家的id不超过十个字符
				var id_input=$("#id").val();
				var reg =/^[0-9a-zA-Z]+$/;
				if(reg.test(id_input) && id_input.length<=10){
						$("#id_message").html("格式正确");
						$("#id_message").css("color","green");
						return true;
				}else{
					$("#id_message").html("格式错误");
					$("#id_message").css("color","red");
					return false;
				}
			}
									
		
			//检测店铺的名称，其长度不得超过20
			function check_shopName(){
				var shopName=$("#shopName").val();
				if(shopName.length<=20 && shopName.length>0)
					{
						$("#shopName_message").html("格式正确");
						$("#shopName_message").css("color","green");
						return true;
					}
				else{
						$("#shopName_message").html("格式错误");
						$("#shopName_message").css("color","red");
						return false;
				}
			}
			
			//检测省份
			function check_province(){
				var province=$("#province").val();
				if(province=="---请选择---")
					{
						$("#province_message").html("请选择省");
						$("#province_message").css("color","red");
						return false;
					}
				return true;
			}
			
			//检测市
			function check_city(){
				var city=$("#city").val();
				if(city=="---请选择---")
				{
					$("#city_message").html("请选择市");
					$("#city_message").css("color","red");
					return false;
				}
			return true;
				
			}
			
			//检测县
			function check_country(){
				var country=$("#country").val();
				if(country=="---请选择---")
				{
					$("#country_message").html("请选择县");
					$("#country_message").css("color","red");
					return false;
				}
			return true;
				
			}
			
			//检测街道，街道长度最大20
			function check_street(){
				var street=$("#street").val();
				if(street.length>20 || street.length==0)
				{
					$("#street_message").html("格式错误");
					$("#street_message").css("color","red");
					return false;
				}else{
					
					$("#street_message").html("格式正确");
					$("#street_message").css("color","green");
					return true;
					
				}
			return true;
				
			}
			
			//检测邮编,6位数字
			function check_code(){
				var code=$("#code").val();
				var reg=/^[0-9]+$/;
				
				if(reg.test(code) && code.length==6)
					{
						$("#code_message").html("格式正确");
						$("#code_message").css("color","green");
						return true;
					}else{
						$("#code_message").html("格式错误");
						$("#code_message").css("color","red");
						return false;
						
					}
				
			}
			
			
			
			//检测店铺的描述，其长度不得超过2000
			function check_intro()
			{
				var introduction=$("#introduction").val();
				if(introduction.length<=2000 && introduction.length>0)
					{
						$("#intro_message").html("格式正确");
						$("#intro_message").css("color","green");
						return true;
					}else{
						$("#intro_message").html("格式错误");
						$("#intro_message").css("color","red");
						return false;						
					}
			}
			//表单提交的触发事件
			function check_submit(){
				if(check_id()+ check_name() + check_password1() + check_password2() + check_tel() +
						check_shopName() + check_address() + check_intro()==8)
					{
						
						return true;
					}else {
						
						return false;
					}
				
			}
		
		</script>
</head>
<body>

	<%@ include file="nav.jsp" %>
	<%@ include file="nav2.jsp" %>
	<div class="container">
			
			
		<div  id="alignCenter">
			<form class="form-horizontal" action="/bookshop/updateShopInfo.shopAction"  enctype="multipart/form-data" method="post" onsubmit="return check_submit()" accept-charset="UTF-8">	
				  <div class="form-group">
				    <label class="col-sm-2 control-label">店铺号</label>
				    <div class="col-sm-7">
				      	<input type="text" class="form-control" id="id" name="s_no" value=${shop.s_no } readonly="readonly">
				    </div>
				    <div class="col-sm-3">
				    	<span id="id_message"></span>
				    </div>
				  </div>
				  
			
				  
				    <div class="form-group">
					    <label class="col-sm-2 control-label">店铺名称</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" id="shopName" name="shopName" value=${shop.shopName }>
					    </div>
					     <div class="col-sm-3">
				    	<span id="shopName_message"></span>
				    </div>
				 	</div>
				 	<div class="form-group">
					    <label class="col-sm-2 control-label">所在省</label>
					    <div class="col-sm-7">
							<select id="province" class="form-control" name="province" onchange="provinceChange()" value=${shop.province }>
								<option>---请选择---</option>
								<option value="河南">河南</option>
								<option value="北京">北京</option>
								<option value="广东">广东</option>
							</select>
					    </div>
					     <div class="col-sm-3">
				    	<span id="province_message"></span>
				    </div>
				 	</div>
				 	<div class="form-group">
					    <label class="col-sm-2 control-label">所在市</label>
					    <div class="col-sm-7">
							<select id="city" name="city" class="form-control" >
								<option>---请选择---</option>
							</select>
					    </div>
					     <div class="col-sm-3">
				    	<span id="city_message"></span>
				    </div>
				 	</div>
				 	<div class="form-group">
					    <label class="col-sm-2 control-label">所在县</label>
					    <div class="col-sm-7">
							<select id="country" name="country" class="form-control">
								<option>---请选择---</option>
							</select>
					    </div>
					     <div class="col-sm-3">
				    	<span id="country_message"></span>
				    </div>
				 	</div>
				 	<div class="form-group">
					    <label class="col-sm-2 control-label">所在街道</label>
					    <div class="col-sm-7">
							<input type="text" class="form-control" id="street" name="street" placeholder="请详细描述街道信息"/>
					    </div>
					     <div class="col-sm-3">
				    		<span id="street_message"></span>
				   		 </div>
				 	</div>
				 	<div class="form-group">
					    <label class="col-sm-2 control-label">邮编</label>
					    <div class="col-sm-7">
							<input type="text" id="code" name="code" class="form-control" value=${shop.postCode} />
					    </div>
					     <div class="col-sm-3">
				    		<span id="code_message"></span>
				    	 </div>
				 	</div>

				<div class="form-group">
				    <label class="col-sm-2 control-label">店铺描述</label>
				    <div class="col-sm-7">
				    	<textarea class=" form-control" rows="8" name="introduction" id="introduction" value=${shop.introduction }></textarea>
				    </div>
				     <div class="col-sm-3">
				    	<span id="intro_message"></span>
				    </div>
				  </div>
				 
			
			 	 <div class="form-group">
				  	<label class="col-sm-2">店铺背景</label>
				  	<div class="col-sm-10">
				  		<img src="/bookshop/img/3.jpg" width="100%"/>
				  	</div>
				  </div>
			 	 <div class="form-group">
				  	<label class="col-sm-2">重新上传</label>
				  	<div class="col-sm-10">
				  		<div class="">
						  		<input type="file"    name="file"/> 
				  		</div>
				  	</div>
				  </div>
				  <div class="form-group">
				    <div class=" col-sm-10">
				      <button type="submit" class="btn btn-default">确认修改</button>
				    </div>
				  </div>
			</form>
		 </div>
	</div>
</body>
</html>