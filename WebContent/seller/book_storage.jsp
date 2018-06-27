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
			$(function(){
				var message=$("#addBookMessage").val();
				if(message=="入库成功" || message=="入库失败")
					{
						alert(message);
					}
			});
			window.onload=function(){
				$("#b_no").bind("blur",check_id);
				$("#name").bind("blur",check_name);
				$("#author").bind("blur",check_author);
				$("#publish").bind("blur",check_publish);
				$("#publish_date").bind("blur",check_publish_date);
				$("#price").bind("blur",check_price);
				$("#sell_price").bind("blur",check_sell_price);
				$("#pur_price").bind("blur",check_pur_price);
				$("#number").bind("blur",check_number);
				document.getElementById("book_info").onblur=check_book_info;
				
				
				
				
			}
			
			
			function check_id()
			{
				//用户的id不超过十个字符
				var id_input=$("#b_no").val();
				var reg =/^[0-9a-zA-Z]+$/;
				if(reg.test(id_input) && id_input.length<=10){
						$("#b_no_message").html("格式正确");
						$("#b_no_message").css("color","green");
						return true;
				}else{
					$("#b_no_message").html("格式错误");
					$("#b_no_message").css("color","red");
					return false;
				}
			}
			
			//检测用户的姓名,只检测长度,不得超过10个字符
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
			//检测作者的姓名,只检测长度,不得超过10个字符
			function check_author(){
				var name_input=$("#author").val();
				if(name_input.length<=10 && name_input.length>0)
					{
						$("#author_message").html("格式正确");
						$("#author_message").css("color","green");
						return true;
					}else{
						$("#author_message").html("格式错误");
						$("#author_message").css("color","red");
						return false;	
					}
			}
			function check_publish(){
				var name_input=$("#publish").val();
				if(name_input.length<=10 && name_input.length>0)
					{
						$("#publish_message").html("格式正确");
						$("#publish_message").css("color","green");
						return true;
					}else{
						$("#publish_message").html("格式错误");
						$("#publish_message").css("color","red");
						return false;	
					}
			}
			
			
			
			//检查出生日期是否已经选择,并且是否小于今天
			function check_publish_date(){
				var now=new Date();
				var publish_date=new Date($("#publish_date").val());
				
				if($("#publish_date").val()==""  )
					{
						$("#publisth_date_message").html("请选择日期");
						$("#publish_date_message").css("color","red");
						return false;
					}else if(now < publish_date) {
						$("#publish_date_message").html("日期范围越界");
						$("#publish_date_message").css("color","red");
						return false;
					}
					else{
						return true;
					}
			}
			
			function check_price()
			{
				var price=$("#price").val();
				var reg =/^[0-9]+$/;
				if(reg.test(price) && price.length>0)
					{
						$("#price_message").html("格式正确");
						$("#price_message").css("color","green");
						return true;
					}else{
						$("#price_message").html("格式错误");
						$("#price_message").css("color","red");
						return false;
					}
			}
			
			//检测售价的输入是否正确
			function check_sell_price(){
				var sell_price=$("#sell_price").val();
				var reg=/^[0-9]+$/;
				if(reg.test(sell_price) && sell_price.length>0)
					{
						$("#sell_price_message").html("格式正确");
						$("#sell_price_message").css("color","green");
						return true;
					}else{
						$("#sell_price_message").html("格式错误");
						$("#sell_price_message").css("color","red");
						return false;
					}
				
				
			}
			//检测售价的输入是否正确
			function check_pur_price(){
				var pur_price=$("#pur_price").val();
				var reg=/^[0-9]+$/;
				if(reg.test(pur_price) && pur_price.length>0)
					{
						$("#pur_price_message").html("格式正确");
						$("#pur_price_message").css("color","green");
						return true;
					}else{
						$("#pur_price_message").html("格式错误");
						$("#pur_price_message").css("color","red");
						return false;
					}
				
				
			}
			//检测数量的输入是否正确
			function check_number(){
				var number=$("#number").val();
				var reg=/^[0-9]+$/;
				if(reg.test(number) && number.length>0)
					{
						$("#number_message").html("格式正确");
						$("#number_message").css("color","green");
						return true;
					}else{
						$("#number_message").html("格式错误");
						$("#number_message").css("color","red");
						return false;
					}
				
				
			}
		
			function check_book_info(){
				//alert("dd");
				var book_info=$("#book_info").val();
				if( book_info.length>0)
					{
						$("#book_info_message").html("格式正确");
						$("#book_info_message").css("color","green");
						return true;
					}else{
						$("#book_info_message").html("格式错误");
						$("#book_info_message").css("color","red");
						return false;	
					}
			}
			
	
			
			
			//表单提交的触发事件
			function check_submit(){
				if(check_id()+ check_name() + check_password1() + check_password2() + check_tel() +
						check_birthday()==6)
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
		<input type="hidden" id="addBookMessage" value="${requestScope.addBookMessage}" />
		<center><h1>图书入库界面</h1></center>
		<!-- <p>
			图书入库的操作应该涉及到数据库的两个表：‘图书’表和‘商家拥有的图书库存表’。
			需要先检查图书编号，如果编号已存在，我们需要使用ajax默认将图书名称、类型、作者、出版社、出版日期、定价给自动补上。
			如果不存在，我们需要将图书编号、图书名称、类型、作者、出版社、出版日期、定价这些信息存到‘图书’表中。
			然后再将图书编号、商家id(应该保存在session当中)、数量、售价、图片地址等存到‘商家拥有的图书库存’ 表中。
		</p> -->
		
		<div class="container">
			
			
		<div  id="alignCenter">
			<form class="form-horizontal" role="form" action="/bookshop/addBook.bookAction" method="post" enctype="multipart/form-data">	
				  <div class="form-group">
				    <label class="col-sm-2 control-label">图书编号</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" name="b_no" id="b_no"  placeholder="请输入图书编号">
				    </div>
				    <div class="col-sm-3">
				    	<span id="b_no_message"></span>
				    </div>
				  </div>
				  
				  <div class="form-group">
					    <label class="col-sm-2 control-label">图书名称</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" name="name" id="name" placeholder="请输入图书名称">
					    </div>
					    <div class="col-sm-3">
					    	<span id="name_message"></span>
					    </div>
				  </div>
				  <div>
				  	
			  	 <div class="form-group">
                       <label class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-7">
                            <select  name="type" class="form-control" id="type">
                                    <option value="文学">文学</option>
                                    <option value="小说">小说</option>
                                    <option value="考试">考试</option>
                                    <option value="计算机">计算机</option>
                                    <option value="外语">外语</option>
                                    <option value="艺术">艺术</option>
                                    <option value="历史">历史</option>
                                    <option value="法律">法律</option>
                                    <option value="励志">励志</option>
                                    <option value="漫画">漫画</option>
                            </select>
                        </div>
                      </div>
				  </div>
				  
				   <div class="form-group">
					    <label class="col-sm-2 control-label">作者</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" id="author" name="author"  placeholder="请输入作者">
					    </div>
					    <div class="col-sm-3">
					    	<span id="author_message"></span>
					    </div>
				  </div>
				  
				   <div class="form-group">
					    <label class="col-sm-2 control-label">出版社</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" id="publish" name="publish" placeholder="请输入出版社">
					    </div>
					    <div class="col-sm-3">
					    	<span id="publish_message"></span>
					    </div>
				  </div>
				  
				   <div class="form-group">
					    <label class="col-sm-2 control-label">出版日期</label>
					    <div class="col-sm-7">
					      <input type="date" class="form-control" id="publish_date" name="publish_date" placeholder="如2017-11-11">
					    </div>
					    <div class="col-sm-3">
					    	<span id="publish_date_message"></span>
					    </div>
				  </div>
				  
				   <div class="form-group">
					    <label class="col-sm-2 control-label">定价</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" id="price" name="price" placeholder="请输入定价">
					    </div>
					    <div class="col-sm-3">
					    	<span id="price_message"></span>
					    </div>
				  </div>
				   <div class="form-group">
					    <label class="col-sm-2 control-label">售价</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" id="sell_price" name="sell_price" placeholder="请输入定价">
					    </div>
					    <div class="col-sm-3">
					    	<span id="sell_price_message"></span>
					    </div>
				  </div>
				  
				   <div class="form-group">
				    <label class="col-sm-2 control-label">数量</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" name="number" id="number" placeholder="请输入姓">
				    </div>
				    <div class="col-sm-3">
					    	<span id="number_message"></span>
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label class="col-sm-2 control-label">进货单价</label>
				    <div class="col-sm-7">
				      <input type="text" class="form-control" name="pur_price" id="pur_price" placeholder="请输入进货单价">
				    </div>
				    <div class="col-sm-3">
					    	<span id="pur_price_message"></span>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="col-sm-2 control-label">图书描述</label>
				    <div class="col-sm-7">
				    	<textarea class=" form-control" name="book_info" id="book_info" rows="3"></textarea>
				    </div>
				    <div class="col-sm-3">
					    	<span id="book_info_message"></span>
				    </div>
				  </div>
				 
			
			 	 <div class="form-group">
				  	<label class="col-sm-2">图书上传</label>
				  	<div class="col-sm-10">
				  		<div class="">
						  		<input type="file"  class=""  name="file1"/> 
						  		<input type="file"  class="form-control-file" name="file2"/> 
						  		<input type="file"  class="form-control-file"  name="file3"/> 
				  		</div>
				  	</div>
				  </div>
				  
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-default">登录</button>
				    </div>
				  </div>
			</form>
		 </div>
		</div>
	</body>
</html>
