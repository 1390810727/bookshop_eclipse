package web.seller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.login.DoLoginDao;
import model.User;
import service.DoLoginService;

public class SellerLoginServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		PrintWriter pw=response.getWriter();
		if("/check_seller".equals(path))
		{
			
			int n=checkSeller(request,response);
			//如果为-1则验证码错误
			if(n==-1) {
				pw.print("验证码错误");
				return;
			}
			//如果为0则账号或密码错误
			if(n==0) {
				pw.println("账号或密码错误");
				return;
			}
			//如果为1则表示登陆成功
			if(n==1)
			{
				pw.println(1);
			}
		
		}
		
		if("/loginSeller".equals(path))
		{
			
			int n=checkSeller(request,response);
			//如果为-1则验证码错误
			if(n==-1) {
				pw.print("验证码错误");
				return;
			}
			//如果为0则账号或密码错误
			if(n==0) {
				pw.println("账号或密码错误");
				return;
			}
			//如果为1则表示登陆成功
			if(n==1)
			{	String id=request.getParameter("id");
				request.getSession().setAttribute("s_id", id);
				request.getSession().setAttribute("seller_name", new DoLoginDao().getSellerName(id));
				response.sendRedirect("/bookshop/getShopList.shopAction");
			}
			
			
		//获取请求过来的参数
		}
		
	}
	/**
	 * 检验管理员登陆的信息
	 * @return 如果返回1则成功，如果返回0则账号密码错误，如果返回-1则验证码错误
	 */
	public int checkSeller(HttpServletRequest request, HttpServletResponse response)
	{
		String code=(String)request.getSession().getAttribute("imgCode");
		String imgCode=request.getParameter("imgCode");
		//System.out.println("code:"+code+"  imgCode:"+imgCode);
		//如果验证码正确，则检验用户名和密码是否正确，否则不进行检测
		if(code.equals(imgCode))
		{
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			User user=new User();
			user.setId(id);
			user.setPassword(password);
			DoLoginDao dld=new DoLoginDao();
			String user_id=dld.doLoginSeller(user);
			if(user_id==null)
			{
				return 0;
			}else {
				return 1;
			}
					
		}else {
			
			
			return -1;
		}
	
	
	}

}
