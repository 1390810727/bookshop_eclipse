package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 处理用户退出登陆的servlet
 * 包括 管理员   商家    顾客的退出登陆的servlet
 * @author KUIKUI
 *
 */
public class LogoutServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		//System.out.println(path);
		if("/admin_logout".equals(path))
		{
			//将session中的id删除，然后返回登陆页面
			request.getSession().removeAttribute("id");
			//重定向到登陆页面
			response.sendRedirect("/bookshop/login/admin_login.jsp");
			
		}
		
		if("/seller_logout".equals(path))
		{
			//将商家的的s_id和seller_name从session中清除
			request.getSession().removeAttribute("seller_name");
			request.getSession().removeAttribute("s_id");
			response.sendRedirect("/bookshop/login/seller_login.jsp");
		}
		
		if("/customer_logout".equals(path))
		{
			//将顾客的c_id和c_name 从session中清除
			//String c_id = (String) request.getSession().getAttribute("c_id");
			request.getSession().removeAttribute("c_id");
			
			request.getSession().removeAttribute("c_name");
			response.sendRedirect("/bookshop/mainPage");
		}
	}
	
	
}
