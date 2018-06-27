<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid"> 
	    <div class="navbar-header">
	        <a class="navbar-brand" href="#">网上书城</a>
	    </div>
	    
	    <c:if test="${sessionScope.c_id != null}">
			 <div>
		        <p class="navbar-text">您好！${sessionScope.c_name }！</p>
		    </div>
		    <div>
		    	<p class="navbar-text"><a href="/bookshop/customer_logout.logout">退出</a></p>
		    </div>
	    </c:if>
		<c:if test="${sessionScope.c_id == null}">
			<div>
				<p class="navbar-text">未登录	！</p>
			</div>
		</c:if>
		
		
	    <form class="navbar-form navbar-right"  style="margin-right: 40px" action="/bookshop/searchByName.searchBookAction"  >
	        <div class="form-group" align="right">
	            <input type="text" class="form-control" id="searchName" name="searchName" value="${requestScope.searchName==null ? "" : requestScope.searchName}">
	            <input type="hidden" name="pageNumber" value="0">
	        </div>
	        <button type="submit" class="btn btn-default">搜索</button>
	    </form>
    </div>
</nav>