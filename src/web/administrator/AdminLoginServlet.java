package web.administrator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.DoLoginService;

public class AdminLoginServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req,resp);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		PrintWriter pw=response.getWriter();
		if("/check_admin".equals(path))
		{
			
			int n=checkAdmin(request,response);
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
		
		if("/loginAdmin".equals(path))
		{
			
			int n=checkAdmin(request,response);
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
				request.getSession().setAttribute("id", id);
				response.sendRedirect("/bookshop/administrator/admin_interface.jsp");
			}
			
			
		//获取请求过来的参数
		}
		
	}
	/**
	 * 检验管理员登陆的信息
	 * @return 如果返回1则成功，如果返回0则账号密码错误，如果返回-1则验证码错误
	 */
	public int checkAdmin(HttpServletRequest request, HttpServletResponse response)
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
			DoLoginService dls=new DoLoginService();
			String user_id=dls.doLoginAdmin(user);
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
