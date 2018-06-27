<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="padding:5px 50px;">
			<div class="container">
				<form id="search" action="/bookshop/searchBook.bookAction" method="get">
					<ul class="nav nav-tabs" id="nav">
					  <li style="float: right;"><a href="#" onclick="javaScript:$('#search').submit();" type="submit">查询</a></li>
					  <li style="float: right; ">
							<input type="text" name="searchContent" class="form-control"/>
					  </li>
					</ul>
				</form>
			</div>
		</div>