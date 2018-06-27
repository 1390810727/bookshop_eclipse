package web.seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.seller.OrderFormDao;
import model.OrderFormInfo;
import model.SameShopBook;

public class OrderFormServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session=request.getSession();
		PrintWriter pw=response.getWriter();
		
		//获得店铺编号
		String s_no=(String)session.getAttribute("s_no");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		
		if("/orderFormList".equals(path)) {
			String status=request.getParameter("status");
			//获取顾客id为c_id的所有订单
			List<SameShopBook> sameShopBookList=null;
			
			//当status传入null时，代表全部订单的查询
			
			OrderFormDao ofd=new OrderFormDao();
			sameShopBookList=ofd.getOrderFormList(s_no, status);
			System.out.println("获取的长度为："+sameShopBookList.size());	
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/seller/order_form2.jsp").forward(request, response);
			return;
		}
		
		if("/deliverBook".equals(path)) {
			//发货，
			String c_id =request.getParameter("c_id");
			String createDate = request.getParameter("createDate");
			
			OrderFormDao ofd=new OrderFormDao();
			if(ofd.deliverBook(createDate, c_id, "待发货", "待收货")) {
				//成功则
				
				response.getWriter().print("发货成功");
			}else {
				System.out.println("发货失败.时间为："+createDate+"  c_id="+c_id);
				response.getWriter().print("发货失败");
			}
			
			
		}
		
		//获取待付款的订单，并转发到订单管理页面
		/**if("/orderFormList".equals(path))
		{
			String status=request.getParameter("status");
			System.out.println(status);
			OrderFormDao ofo=new OrderFormDao();
			
			List<OrderFormInfo> orderFormInfoList=ofo.orderFormInfoList(s_no, status);
			request.setAttribute("orderFormInfoList", orderFormInfoList);
			
			//转发到订单管理页面
			request.getRequestDispatcher("seller/order_form.jsp").forward(request, response);
		}
		
		//更新物流信息
		if("/updateLog".equals(path))
		{
			String o_no=request.getParameter("o_no");
			String newAddress=request.getParameter("newAddress");
			OrderFormDao ofo=new OrderFormDao();
			if(ofo.updateLog(o_no, newAddress))
			{
				pw.println("更新成功");
			}
			else {
				pw.println("更新失败");
			}
			
		}
		
		//更新价格
		if("/updatePrice".equals(path))
		{
			String o_no=request.getParameter("o_no");
			double price=Double.parseDouble(request.getParameter("price"));
			OrderFormDao ofo=new OrderFormDao();
			if(ofo.updatePrice(o_no, price))
			{
				pw.write("1");
			}else{
				pw.print("0");
			}
		}
		
		*/
		
	}

	
}
