package web.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.main.CustomerDao;
import dao.main.OrderFormDao;
import dao.main.ShopCardDao;
import model.Address;
import model.BookInfo;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import web.main.bean.Book;
import web.main.bean.Logistic;
import web.main.bean.OrderForm;
import web.main.bean.OrderFormInfo;
import web.main.bean.SameShopBook;

public class OrderFormServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		if("/submitOrderForm".equals(path)){
			
			//从session当中取到c_id
			String c_id=(String) request.getSession().getAttribute("c_id");
			String jsonStr=request.getParameter("content");
			System.out.println("获得的json:"+jsonStr);
				
			//将json字符串转换成json对象
			Book[] jsonArr=(Book[]) JSONArray.toArray(JSONArray.fromObject(jsonStr),Book.class);
			OrderForm orderForm=null;
			List<OrderForm> orderFormList=new ArrayList<>();
			Set<String> strSet=new HashSet<>();//辅助集合
			for(Book b:jsonArr) {
				
				orderForm=new OrderForm();
				orderForm.setC_id(c_id);
				orderForm.setB_no(b.getB_no());
				orderForm.setS_no(b.getS_no());
				orderForm.setNumber(b.getNumber());
				orderForm.setPrice(b.getPrice());
				orderForm.setStatus("待付款");
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String now=sdf.format(date);
				orderForm.setCreateDate(now);
				orderForm.setUpdateDate(now);
				
				orderFormList.add(orderForm);
				
				strSet.add(b.getS_no());
				System.out.println(b.getB_no()+"  "+b.getS_no()+"  "+b.getNumber()+"   "+b.getPrice());
			}
			OrderFormDao ofd=new OrderFormDao();
			ofd.createOrderForm(orderFormList);
			
			List<SameShopBook> ssbList=new ArrayList<>();
			List<OrderFormInfo> ofiList=null;
			SameShopBook sameShopBook=null;
			OrderFormInfo orderFormInfo=null;
			BookInfo bookInfo=null;
			//重新生成一个便于再付款界面显示的集合
			Iterator<String> iter=strSet.iterator();
			while(iter.hasNext()) {
				String s_no=iter.next();
				String shopName=ofd.getShopNameByS_no(s_no);
				sameShopBook=new SameShopBook();
			
				ofiList=new ArrayList<>();
				
				int sumNumber=0;
				double sumPrice=0;
				for(OrderForm of:orderFormList) {
					if( of.getS_no()!=null  && of.getS_no().equals(s_no)) {
						sumNumber+=of.getNumber();
						sumPrice+=of.getPrice()*of.getNumber();
						
						orderFormInfo=new OrderFormInfo();
						bookInfo=ofd.getBookInfo(of.getS_no(), of.getB_no());
						orderFormInfo.setBookName(bookInfo.getName());
						orderFormInfo.setAuthor(bookInfo.getAuthor());
						orderFormInfo.setType(bookInfo.getType());
						orderFormInfo.setImgAddress(bookInfo.getImgAddress1());
						orderFormInfo.setOrderForm(of);
						
						ofiList.add(orderFormInfo);
					}
				}
				sameShopBook.setS_no(s_no);
				sameShopBook.setShopName(shopName);
				sameShopBook.setSumNumber(sumNumber);
				sameShopBook.setSumPrice(sumPrice);
				sameShopBook.setOrderFormInfoList(ofiList);
				ssbList.add(sameShopBook);
			}
			
			
			
			//将sameShopBook存到session当中，然后重定向到待付款页面
				
				request.getSession().setAttribute("sameShopBookList", ssbList);
				response.sendRedirect("/bookshop/main/paying.jsp");
			 return;
		}
		
		
		//付款的servlet地址
		if("/payOrderForm".equals(path)) {
			
			String c_id=(String)request.getSession().getAttribute("c_id");
			//从session当中将待付款的信息取来
			
			List<SameShopBook> sameShopBookList=(List<SameShopBook>)request.getSession().getAttribute("sameShopBookList");
			//获得request当中的店铺号
			String s_no=request.getParameter("s_no");
			SameShopBook sameShopBook=null;
			
			//将正在付款店铺信息获取，再sameShopBook当中
			for(SameShopBook ssb:sameShopBookList) {
				
				if( ssb.getS_no()!=null && ssb.getS_no().equals(s_no))
				{
					sameShopBook=ssb;
				}
			}
			
			//获取该顾客的收货地址的集合
			OrderFormDao ofd=new OrderFormDao();
			
			List<Address> addressList=ofd.getAddressList(c_id);
			
			//将sameShopBook 和addressList存到request中转发到orderForm.jsp
			request.setAttribute("sameShopBook", sameShopBook);
			request.setAttribute("addressList",addressList);
			request.getRequestDispatcher("main/orderForm.jsp").forward(request, response);
			return;
		}
		
		//做付款操作的servlet地址
		if("/payAction".equals(path)) {
			
			//从request中获得相关数据.此处暂时先获得收货地址，付款方式暂且不做开发
			int a_no=Integer.parseInt(request.getParameter("address"));
			String s_no=request.getParameter("s_no");
			String c_id =(String) request.getSession().getAttribute("c_id");
			
			System.out.println("订单号："+a_no+"  店铺号："+s_no);
			//从session当中获得待付款的对象
			List<SameShopBook> sameShopBookList=(List<SameShopBook>)request.getSession().getAttribute("sameShopBookList");
			SameShopBook sameShopBook=null;
			
			//将正在付款店铺信息获取，再sameShopBook当中
			for(SameShopBook ssb:sameShopBookList) {
				
				if( ssb.getS_no()!=null && ssb.getS_no().equals(s_no))
				{
					sameShopBook=ssb;
				}
			}
			List<OrderFormInfo> orderFormInfoList=sameShopBook.getOrderFormInfoList();
			
			List<OrderForm> orderFormList=new ArrayList<>();
			OrderForm orderForm=null;
			for(OrderFormInfo ofi:orderFormInfoList) {
				
				orderForm=ofi.getOrderForm();
				orderForm.setStatus("待发货");
				orderForm.setA_no(a_no);
				orderFormList.add(orderForm);
				
			}
			
			OrderFormDao ofd=new OrderFormDao();
			if(orderFormList!=null &&  ofd.createOrderForm(orderFormList)) {
				//如果成功，则重定向到成功界面
				ofd.deleteOrderForm("待付款", c_id);
				response.sendRedirect("/bookshop/main/success.jsp");
			}
			return;
		}
		
		
		if("/allOrderForm".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//获取顾客id为c_id的所有订单
			List<SameShopBook> sameShopBookList=null;
			
			//当status传入null时，代表全部订单的查询
			sameShopBookList=getOrderFormList(c_id, null);
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		if("/paying".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//获取顾客id为c_id的所有订单
			List<SameShopBook> sameShopBookList=null;
			
			//当status传入null时，代表全部订单的查询
			sameShopBookList=getOrderFormList(c_id, "待付款");
			
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
	
		
		if("/delivering".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//获取顾客id为c_id的所有订单
			List<SameShopBook> sameShopBookList=null;
			
			//当status传入null时，代表全部订单的查询
			sameShopBookList=getOrderFormList(c_id, "待发货");
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		
		if("/takingDelivery".equals(path)){
			String c_id=(String)request.getSession().getAttribute("c_id");
			//获取顾客id为c_id的所有订单
			List<SameShopBook> sameShopBookList=null;
			
			//当status传入null时，代表全部订单的查询
			sameShopBookList=getOrderFormList(c_id, "待收货");
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		
		if("/evaluating".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//获取顾客id为c_id的所有订单
			List<SameShopBook> sameShopBookList=null;
			
			//当status传入null时，代表全部订单的查询
			sameShopBookList=getOrderFormList(c_id, "待评价");
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		
		if("/returnGoods".equals(path)){
			String s_no=request.getParameter("s_no");
			String createDate=request.getParameter("createDate");
			String status=request.getParameter("status");
			String c_id=(String)request.getSession().getAttribute("c_id");
			
			
			//修改数据库，将指定的订单状态设为待退货 
			OrderFormDao ofd=new OrderFormDao();
			if(ofd.returnGoods(createDate, status, s_no,c_id)) {
				request.setAttribute("message", "退货申请成功，请耐心等待");
				request.getRequestDispatcher("/main/order_success.jsp").forward(request, response);
			}else {
				
				request.setAttribute("message", "退货申请失败，请重新尝试");
				request.getRequestDispatcher("/main/order_success.jsp").forward(request, response);
			}
			
			
			return;
		}
		
		if("/showLogistic".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");			
			
			String createDate=request.getParameter("createDate");
			OrderFormDao ofd=new OrderFormDao();
			List<Logistic> logisticList=ofd.getLogistic(c_id, createDate);
			
			request.setAttribute("logisticList", logisticList);
			request.getRequestDispatcher("/main/logistic.jsp");
			
		}
		
		if("/addAddress".equals(path)) {
			//从request当中取数据
			String s_no=request.getParameter("s_no");
			String c_id=(String)request.getSession().getAttribute("c_id");
			Address address=new Address();
			
			address.setC_id(c_id);
			address.setName(request.getParameter("name"));
			address.setProvince(request.getParameter("province"));
			address.setCity(request.getParameter("city"));
			address.setCountry(request.getParameter("country"));
			address.setStreet(request.getParameter("street"));
			address.setPost_code(request.getParameter("post_code"));
			address.setTell_no(request.getParameter("tell_no"));
			
			new CustomerDao().addAddress(address);
			response.sendRedirect("/bookshop/payOrderForm.c_orderFormAction?s_no="+s_no);
			
			
		}
		
	}
	
	
	protected  List<SameShopBook> getOrderFormList(String c_id, String status) {
		List<SameShopBook> sameShopBookList=null;
		OrderFormDao ofd=new OrderFormDao();
		sameShopBookList=ofd.getOrderFormList(c_id, status);
		return sameShopBookList;
	}
	
}
