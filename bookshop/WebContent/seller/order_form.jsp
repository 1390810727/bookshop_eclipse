<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/bookshop/css/bootstrap.min.css" />
		<script type="text/javascript" src="/bookshop/js/jquery-3.2.1.js" ></script>
		<script type="text/javascript" src="/bookshop/js/bootstrap.js" ></script>
		
		<script type="text/javascript">
			$(function(){
				$(".shut").bind("click",function(){
					$(".popOrderInfo").hide(1000);
				});
			});
			
			function showOrderInfo(t)
			{
				//$(t).parent().next().css("background-color","green");
				$(t).parent().parent().next().css("z-index","1");
				$(".popBookInfo").hide();
				$(t).parent().parent().next().toggle(1000);
			}
			function update_log(t){
				$(t).attr("contenteditable","true");
				$(t).css("backgroundColor","white");
				
				
			}
			function update(t){
				//使用ajax来更新
				var o_no=$(t).next().val();
			
				var newAddress=$(t).text();
				var old_address=$(t).next().next().val();
				$(t).attr("contenteditable","false");
				//alert(address+"==点我一下"+(address.trim()=="点我进行修改"));
				if(newAddress!=old_address && newAddress.trim()!="点我一下")
					{
						if(confirm("确定要更改物流信息吗？"))
							{
								//进行ajax操作
								//location=encodeURI("/bookshop/updateLog.orderFormAction?o_no="+o_no+"&newAddress="+newAddress);
								$.ajax({
									"url":"/bookshop/updateLog.orderFormAction",
									"type":"post",
									"dataType":"text",
									"data":"o_no="+o_no+"&newAddress="+newAddress,
									"success":function(stocks){
										if(stocks.trim()=="更新成功")
											{
												if(confirm("更新成功！是否更新页面?"))
													{
														location.reload();
													}
											}else{
												alert("更新失败")
											}
									}
								});
								
								
							}
					
					}
			}
			//单价更新之前的值
			var beforeVal;
			function update_price(t){
				//alert("gengxin ");
				$(t).removeAttr("readonly");
				$(t).focus();
				beforeVal=parseInt($(t).val());
			}
			function update_p(t){
				var reg=/^[0-9]+$/;
				if( ! reg.test($(t).val().trim()))
					{
						alert("格式错误，请输入数字");
						$(t).val(beforeVal);
						return;
					}
				alert("test");
				var afterVal=parseInt($(t).val().trim());
				var o_no=$(t).next().val().trim();
				alert(afterVal+" ---- "+$(t).val().trim().length);
				//alert(o_no+"   "+o_no.length);
				//将该数用ajax存到数据库里
				if(afterVal != beforeVal)
					{
					
						if(confirm("确定要更改价格吗？"))
							{
								$.ajax({
									url:"/bookshop/updatePrice.orderFormAction",
									async:true,
									type:"post",
									dataType:"text",
									data:"o_no="+o_no+"&price="+afterVal,
									success:function(stocks){
										alert(stocks);
										if(stocks.trim()=="1")
											{
												if(confirm("更新成功！是否更新页面?"))
													{
														location.reload();
													}
											}else{
												alert("更新失败");
											}
									}
								});
							}
					}
				
			}
			
			
		</script>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<%@ include file="nav2.jsp" %>
	
	<div class="container">
		<ul class="nav nav-tabs nav-justified">
		  <li class="active"><a href="/bookshop/orderFormList.orderFormAction?status=待付款">待付款</a></li>
		  <li><a href="/bookshop/orderFormList.orderFormAction?status=待发货">待发货</a></li>
		  <li><a href="/bookshop/orderFormList.orderFormAction?status=待收货">待收货</a></li>
		  <li><a href="/bookshop/orderFormList.orderFormAction?status=待评价">待评价</a></li>
		</ul>
		<div class="container">
			<table class="table table-hover">
				<thead>
					<tr>
						<td>顾客</td><td>图书</td><td>数量</td><td>单价</td><td>总价</td><td>生成日期</td><td>更改日期</td><td>订单状态</td><td>操作</td>
					</tr>
				</thead>
			</table>
		
			<c:forEach items="${requestScope.orderFormInfoList}" var="orderFormInfo">
				<c:set var="orderForm" value="${orderFormInfo.orderForm }"></c:set>
				<c:set var="logList" value="${orderFormInfo.logList }"></c:set>
					<table class="table table-hover">
						<tr onclick="showOrderInfo(this)">
							<td>${orderForm.customerName }</td><td>${orderForm.bookName }</td><td>${orderForm.number}</td>
							<td>${orderForm.price }</td><td>${orderForm.price*orderForm.number }</td>
							<td>${orderForm.create_date}</td><td>${orderForm.update_date}</td>
							<td>${orderForm.status }</td>
							<td>
								<a href="#">操作</a>
							</td>
						</tr>
					</table>
					<div  class="popOrderInfo" style="display: none;position: fixed; top: 150px;left: 250px; border: solid 0px; width: 60%; ">
		    			<div class="jumbotron" style="height: 600px; border: solid 4px blue; overflow: auto;">
					        <button class="btn btn-primary shut" style="position: absolute; top: 3px; right: 3px;">关闭</button>
					        <div class="col-lg-6 col-sm-6 col-xs-12" style="height: 100%;">
					        	<img src=${orderForm.imgAddress}  width="100%" style="max-height: 300px;"/>
					        	<p style="position: absolute; bottom: 5px;">
						        	<a class="btn btn-primary" href="#">接受</a>
						        	<a class="btn btn-primary" href="#">发货</a>
					        	</p>
					        </div>
					        <div class="col-lg-6 col-sm-6 col-xs-12">
					        	<table class="table">
					        		<tr>
					        			<td>顾客id:</td><td>${orderForm.c_id}</td>
					        		</tr>
					        		<tr>
					        			<td>顾客名称:</td><td>${orderForm.customerName}</td>
					        		</tr>
					        		<tr>
					        			<td>图书编号:</td><td>${orderForm.b_no}</td>
					        		</tr>
					        		<tr>
					        			<td>图书名:</td><td>${orderForm.bookName}</td>
					        		</tr>
					        		<tr>
					        			<td>数量:</td><td>${orderForm.number}</td>
					        		</tr>
					        		<tr>
					        			<td>单价:</td>
					        			<td>
					        				<input value="${orderForm.price}" onclick="update_price(this)" onblur="update_p(this)" readonly="readonly" style="display: inline-block; border: solid 1px white; width: 100%;height: 100%" />
					        				<input type="hidden" value="${orderForm.o_no}">
					        			
					        			</td>
					        		</tr>
					        		<tr>
					        			<td>总价:</td><td>${orderForm.price*orderForm.number }</td>
					        		</tr>
					        		<tr>
					        			<td>创建日期:</td><td>${orderForm.create_date}</td>
					        		</tr>
					        		<tr>
					        			<td>订单状态:</td><td>${orderForm.status}</td>
					        		</tr>
					        		<tr>
					        			<td>更新日期:</td><td>${orderForm.update_date}</td>
					        		</tr>
					        		<tr>
					        			<td>更新物流:</td>
					        			<td>
											<c:set value="${fn:length(logList)}" var="length"></c:set>
											<div onclick="update_log(this)" onblur="update(this)" style="display: inline-block; border: solid 1px white; width: 100%;height: 100%">
												点我一下
											</div>
											<input type="hidden" value=${orderForm.o_no}>
											<input type="hidden" value=${logList[length-1].address}>
										</td>
					        		</tr>
					        		<tr >
					        			<td rowspan="5">物流信息:</td>
					        			<td rowspan="4">
					        				<c:forEach var="logistics" items="${logList}">
					        					<span>已到达:${logistics.address} </span><span>${logistics.date }</span><br>
					        				</c:forEach>
					        			</td>
					        		</tr>
					        		
					        		
					        	</table>
					        </div>
					   </div>
		    		</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>