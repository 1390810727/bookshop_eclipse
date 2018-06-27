package web.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.seller.BookSaleDao;
import model.SaleAmount;
import model.SaleNumber;

public class SaleServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session=request.getSession();
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		//System.out.println(path);
		
		if("/searchBySale".equals(path))
		{
			String date=request.getParameter("date");
			String s_no=(String)session.getAttribute("s_no");
			System.out.println("日期wei:"+date);
			
			BookSaleDao bsd=new BookSaleDao();
			List<SaleAmount> saleList=bsd.saleAmountSearch(s_no, date); 
			request.setAttribute("saleList", saleList);
			request.setAttribute("method", "sale");
			request.getRequestDispatcher("seller/book_sale.jsp").forward(request, response);;
			//接下来按销售额查找
		}
		
		//按销量查找
		if("/searchByNumber".equals(path))
		{
			String date=request.getParameter("date");
			String s_no=(String)session.getAttribute("s_no");
			System.out.println(date);
			BookSaleDao bsd=new BookSaleDao();
			List<SaleNumber> saleList=bsd.saleNumberSearch(s_no, date); 
			request.setAttribute("saleList", saleList);
			request.setAttribute("method", "number");
			request.getRequestDispatcher("/seller/book_sale.jsp").forward(request, response);
		}
		
		
		
	}
	

}
