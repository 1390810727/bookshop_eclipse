package web.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.main.SearchBookDao;
import dao.main.SellWellDao;
import model.BookInfo;
import model.RecomendBook;

/**
 * ͼ��������һЩservlet
 * @author KUIKUI
 *
 */
public class SearchBookServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		
		if("/searchByName".equals(path)) {
			String searchName=request.getParameter("searchName");
			int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			SearchBookDao sbd=new SearchBookDao();
			List<RecomendBook> recomendBookList= sbd.searchByName(searchName, pageNumber, 6);
			
			//��ȡ������ͼ�飬����ǰʮ��ͼ��
			
			SellWellDao swd=new SellWellDao();
			
			//��ñ���
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			String month=sdf.format(date);
			
			//��ҳ����ʾ����ǰʮ��ͼ��
			int number=10;
			List<BookInfo> bookInfoList=swd.getSellWellBook(month, number);
			
			//ת����main.jsp
			request.setAttribute("searchName", searchName);
			request.setAttribute("pageFlag", "searchName");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("recomendBookList", recomendBookList);
			request.setAttribute("bookInfoList", bookInfoList);
			
			request.getRequestDispatcher("main/main.jsp").forward(request, response);
			
			return;
		}
		
		if("/searchByType".equals(path)) {
			String searchType=request.getParameter("searchType");
			int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			SearchBookDao sbd=new SearchBookDao();
			System.out.println(searchType);
			List<RecomendBook> recomendBookList= sbd.searchByType(searchType, pageNumber, 6);
			
			//��ȡ������ͼ�飬����ǰʮ��ͼ��
			
			SellWellDao swd=new SellWellDao();
			
			//��ñ���
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			String month=sdf.format(date);
			
			//��ҳ����ʾ����ǰʮ��ͼ��
			int number=10;
			List<BookInfo> bookInfoList=swd.getSellWellBook(month, number);
			
			//ת����main.jsp
			request.setAttribute("searchType", searchType);
			request.setAttribute("pageFlag", "searchType");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("recomendBookList", recomendBookList);
			request.setAttribute("bookInfoList", bookInfoList);
			
			request.getRequestDispatcher("main/main.jsp").forward(request, response);
			
			return;
		}
		
		if("searchByAuthor".equals(path)) {
			
			
		}
	}

	
	
	
}
