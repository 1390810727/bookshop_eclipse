package web.seller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SellerLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String servletPath=request.getServletPath();
		
		System.out.println(servletPath);
		if("/seller/sellerRegister.jsp".equals(servletPath)) {
			chain.doFilter(request, response);
			return;
		}
		
		
		
		String s_id =(String) request.getSession().getAttribute("s_id");
		if(s_id == null) {
			//δ��¼,�ض��򵽵�½ҳ��
			response.sendRedirect("/bookshop/login/seller_login.jsp");
			
		} else {
			//�ѵ�½����request��response��󴫵�
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	

}
