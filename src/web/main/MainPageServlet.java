package web.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����Ŀ����ҳ���ʵ�ַ
 * @author KUIKUI
 *
 */
public class MainPageServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/bookshop/randomBook?pageNumber=0");
		
		
	}
	
	
}

