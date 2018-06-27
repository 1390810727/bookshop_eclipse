package web.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.login.DoLoginDao;
import model.User;

public class CustomerLoginServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		if("/doLoginCustomer".equals(path)) {
			String c_id=request.getParameter("c_id");
			String password=request.getParameter("password");
			String name=null;
			User user=new User();
			user.setId(c_id);
			user.setPassword(password);
			
			DoLoginDao dld=new DoLoginDao();
			c_id=dld.doLoginCustomer(user);
			
			
			if(c_id!=null)
			{
				//将c_id和name存到session中
				
				name=dld.getCustomerName(c_id);
				
				request.getSession().setAttribute("c_id", c_id);
				request.getSession().setAttribute("c_name", name);
			}
			System.out.println(c_id+"----"+name);
			
			
			
			//转发到主页中去
			request.setAttribute("c_id", c_id);
			request.setAttribute("name", name);
			request.getRequestDispatcher("mainPage").forward(request, response);
			
			
		}
		
		
		
		
		
	}

	
}
