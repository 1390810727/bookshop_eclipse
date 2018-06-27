package web.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.main.ShopCardDao;
import model.ShopCard;
import model.ShopCardBook;
import net.sf.json.JSONArray;
import web.main.bean.Book;
import web.main.bean.OrderForm;

/**
 * 
 * 与浏览器异步通信
 * @author KUIKUI
 *
 */
public class ShopCardServlet extends HttpServlet {

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
		
		//获取项目的地址
		String imgPath=request.getSession().getServletContext().getRealPath("");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		
		//将图书加入购物车的地址
		if("/addBook".equals(path)) {
			
				//从session中获得顾客id
				String c_id=(String) request.getSession().getAttribute("c_id");
				if(c_id != null && !"".equals(c_id))
				{
					
					//从request当中获得店铺号和图书编号
					String s_no=request.getParameter("s_no");
					String b_no=request.getParameter("b_no");
					int number=Integer.parseInt(request.getParameter("number"));
					
					ShopCard shopCard=new ShopCard();
					shopCard.setB_no(b_no);
					shopCard.setC_id(c_id);
					shopCard.setS_no(s_no);
					shopCard.setNumber(number);
					
					
					ShopCardDao scd=new ShopCardDao();
					
					if(scd.addBook(shopCard)) {
						response.getWriter().print("添加成功");
					}else {
						response.getWriter().print("添加失败");
					}
				}else {
					response.getWriter().print("请先登陆");
				}
		}
		
		/**
		 * 加载购物车中的图书列表（采用异步方式向服务器发送请求，所以应使用json字符串）
		 * 只根据 顾客的id便可以获得该用户的购物车  的图书列表
		 */
		if("/loadBook".equals(path)) {
			//从session中获得顾客的id
			String c_id=(String)request.getSession().getAttribute("c_id");
			
			ShopCardDao scd=new ShopCardDao();
			List<ShopCardBook> scbList=new ArrayList<>();
			scbList=scd.loadBook(c_id);
			
			System.out.println(scbList);
			if(scbList != null) //如果获得图书列表，将图书列表转换未json字符串发送到客户端
			{
				JSONArray jsonArray=JSONArray.fromObject(scbList);
				String jsonStr=jsonArray.toString();
				System.out.println(jsonArray.size()+"   "+jsonStr);
				response.getWriter().print(jsonStr);
			}else {
				response.getWriter().print("加载失败");
			}
		}
		
		//根据浏览器传来的number(保存在request中)
		if("/changeNumber".equals(path)) {
			String s_no=request.getParameter("s_no");
			String b_no=request.getParameter("b_no");
			int number=Integer.parseInt(request.getParameter("number"));
			
			//从session中获得顾客id
			String c_id=(String)request.getSession().getAttribute("c_id");
			
			System.out.println(s_no+"  "+b_no+" "+number+" "+c_id);
			ShopCardDao scd=new ShopCardDao();
			boolean isChange=scd.changeNumber(c_id, s_no, b_no, number);
			//如果成功则返回成功，失败则返回失败
			if(isChange) {
				response.getWriter().print("成功");
			}else {
				response.getWriter().println("失败");
			}
		}
		
		if("/deleteBook".equals(path)) {
			//从session当中取到c_id
			String c_id=(String) request.getSession().getAttribute("c_id");
			String jsonStr=request.getParameter("content");
			System.out.println(jsonStr);
			
			//将json字符串转换成json对象
			Book[] jsonArr=(Book[]) JSONArray.toArray(JSONArray.fromObject(jsonStr),Book.class);
			
			ShopCardDao scd=new ShopCardDao();
			int n=0;
			for(Book b:jsonArr) {
				if(scd.deleteShopCard(c_id, b.getS_no(), b.getB_no())) {
					n++;
				}
			}
			//将删除的条数返回给浏览器
			response.getWriter().print(n);
			
			
		}
		
		
	}
}
