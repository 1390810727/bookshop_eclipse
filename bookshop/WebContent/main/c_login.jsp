<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<script>
			function show(t){
				//$(t).next().show();
				$("#search_sale").show().animate({top:'0px'}).show(500);
			}
			function search_shut(){
				$("#search_sale").hide();
				
			}
		</script>
		<div class="panel panel-primary"  style=" display: none ; position: fixed; top: -500px; right: 10px; width: 30%;" id="search_sale">
		    <div class="panel-heading" style="position: relative;">
		        <h3 class="panel-title">请登陆</h3>
		        <button style="position: absolute; top:3px ; right: 3px;" class=" btn btn-danger" onclick="search_shut()">关闭</button>
		    </div>
		    <div style="padding: 40px">
		    	<form method="post" action="/bookshop/doLoginCustomer.customerLogin">
			       	
			       	<div class="row">
			       		账号：<input type="text" class="form-control" name="c_id">
			       	</div>
			       	<div class="row">
			       		密码：<input type="password" class="form-control" name="password">
			       	</div>
			       	<div class="" style="margin: 30px auto;">
			       		<input  class="btn btn-default"  type="submit"  value="登陆">
			       	</div>
		    	</form>
		    </div>
		</div>
