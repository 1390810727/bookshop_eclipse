package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * �����û��˳���½��servlet
 * ���� ����Ա   �̼�    �˿͵��˳���½��servlet
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
			//��session�е�idɾ����Ȼ�󷵻ص�½ҳ��
			request.getSession().removeAttribute("id");
			//�ض��򵽵�½ҳ��
			response.sendRedirect("/bookshop/login/admin_login.jsp");
			
		}
		
		if("/seller_logout".equals(path))
		{
			//���̼ҵĵ�s_id��seller_name��session�����
			request.getSession().removeAttribute("seller_name");
			request.getSession().removeAttribute("s_id");
			response.sendRedirect("/bookshop/login/seller_login.jsp");
		}
		
		if("/customer_logout".equals(path))
		{
			//���˿͵�c_id��c_name ��session�����
			//String c_id = (String) request.getSession().getAttribute("c_id");
			request.getSession().removeAttribute("c_id");
			
			request.getSession().removeAttribute("c_name");
			response.sendRedirect("/bookshop/mainPage");
		}
	}
	
	
}
