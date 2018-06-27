package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
//			String path=request.getServletPath();
//			String contextPath=request.getContextPath();
//			String url=request.getRequestURI();
			String uri=request.getRequestURI();
//			System.out.println(" path:"+ path+"   contexPath:"+contextPath+"  url:"+url+"  uri: "+uri);
			String path=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
			System.out.println(path);
			PrintWriter pw=response.getWriter();
			
			//���ajax�����Ĳ���
			
			String id =request.getParameter("id");
			if("/check_ajax".equals(path))
			{
				if("king".equals(id))
				{
					System.out.println(id+"���û����Ѿ�����");
					pw.println("���û����Ѿ�����");
				}else {
					System.out.println(id+"���û�������ʹ��");
					pw.println("���û�����ʹ��");
				}
			}
			if("/showShopCard".equals(path)) {
				pw.print("����һ��test");
				
				
			}
			
			
			
	}
	

}
