package web.main;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerLoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("doFilter");
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String servletPath=request.getServletPath();
		if("/main/login.jsp".equals(servletPath)) {
			chain.doFilter(request, response);
			return;
		}
		
		
		//���session�����Ƿ��й˿͵�½����Ϣ
		String c_id=(String) request.getSession().getAttribute("c_id");
		if(c_id == null) {
			//δ��¼���ض��򵽵�½����
			
			response.sendRedirect("/bookshop/main/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
