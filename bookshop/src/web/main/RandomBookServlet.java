package web.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.main.RandomBookDao;
import dao.main.SellWellDao;
import model.BookInfo;
import model.RecomendBook;

/**
 * 本项目的主页访问地址
 * @author KUIKUI
 *
 */
public class RandomBookServlet extends HttpServlet {

	/**
	 * 1.获取前10的畅销图书
	 * 2.今日推荐的图书，一般有系统管理员去推荐 或者  添加刚上架的图书
	 * 对于刚上架的图书如何选择的问题：我们随机生成一个随机数，从该随机数开始搜索 从数据库中取10 条刚入库的图书
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//获得pageNumber
		
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		System.out.println("当前页数："+pageNumber+"   测试");
		//推荐图书的条数
		int searchNumber=6;
		
		RandomBookDao rbd=new RandomBookDao();
		List<RecomendBook> recomendBookList=rbd.getBookInfo(pageNumber, searchNumber);
		
		//获取畅销的图书，排行前十的图书
		
		SellWellDao swd=new SellWellDao();
		
		//获得本月
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String month=sdf.format(date);
		
		//主页中显示排行前十的图书
		int number=10;
		List<BookInfo> bookInfoList=swd.getSellWellBook(month, number);
		
		//转发给main.jsp
		request.setAttribute("pageFlag", "mainPage");
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("recomendBookList", recomendBookList);
		request.setAttribute("bookInfoList", bookInfoList);
		
		request.getRequestDispatcher("main/main.jsp").forward(request, response);
		return;
	}

}
