<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar navbar-default" role="navigation">
		    <div class="container-fluid"> 
			    <div class="navbar-header">
			        <a class="navbar-brand" href="#">bookshop系统管理</a>
			    </div>
		    <!--<form class="navbar-form navbar-right" role="search">
		        <div class="form-group">
		            <input type="text" class="form-control" placeholder="Search">
		        </div>
		        <button type="submit" class="btn btn-default">提交</button>
		    </form>-->
		    	<div class="navbar-form navbar-right">
		    		<span class="form-control">${sessionScope.id }</span>
		    		<span><%@ include file="../tools/localTime.html" %></span>
		    		<a href="admin_logout.logout">[退出]</a>
		    	</div>
		    
		    
		    </div>
		</nav>