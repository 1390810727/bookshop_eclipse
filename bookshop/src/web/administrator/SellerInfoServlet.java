package web.administrator;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.seller.SellerSalesDao;
import model.SellerInfo;


public class SellerInfoServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session=request.getSession();
		PrintWriter pw=response.getWriter();
		
		String s_no=(String)session.getAttribute("s_no");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		
		if("/sellerInfo".equals(path))
		{
			//����������Ϣ��ʾ10��Ԫ��
			int num=10;
			
			//������ݣ��·ݣ�����
			
			Date date=new Date();
			SimpleDateFormat dayFormat=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat monthFormat=new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
			
			String day= dayFormat.format(date);
			String month= monthFormat.format(date);
			String year= yearFormat.format(date);
			
			SellerSalesDao ssd=new SellerSalesDao();
			List<SellerInfo> daySaleNumber=ssd.getSaleNumber(day, num);
			List<SellerInfo> monthSaleNumber=ssd.getSaleNumber(month, num);
			List<SellerInfo> yearSaleNumber=ssd.getSaleNumber(year, num);
			List<SellerInfo> daySaleAmount=ssd.getSaleAmount(day, num);
			List<SellerInfo> monthSaleAmount=ssd.getSaleAmount(month, num);
			List<SellerInfo> yearSaleAmount=ssd.getSaleAmount(year, num);
			
			//Ȼ��浽request�ת����seller_info.jspҳ����
			request.setAttribute("daySaleNumber", daySaleNumber);
			request.setAttribute("monthSaleNumber", monthSaleNumber);
			request.setAttribute("yearSaleNumber", yearSaleNumber);
			request.setAttribute("daySaleAmount", daySaleAmount);
			request.setAttribute("monthSaleAmount", monthSaleAmount);
			request.setAttribute("yearSaleAmount", yearSaleAmount);
			
			request.getRequestDispatcher("administrator/seller_info.jsp").forward(request, response);
			
			
			
		}
		
		
		
		
		//��ȡ�������е�servlet��ַ������һ���б�
		if("/sellerSaleNumber".equals(path))
		{
			
		}
		
		//��ȡ���۶�
		if("/sellerSaleAmount".equals(path))
		{
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
