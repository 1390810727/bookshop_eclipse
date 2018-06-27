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
 * ����Ŀ����ҳ���ʵ�ַ
 * @author KUIKUI
 *
 */
public class RandomBookServlet extends HttpServlet {

	/**
	 * 1.��ȡǰ10�ĳ���ͼ��
	 * 2.�����Ƽ���ͼ�飬һ����ϵͳ����Աȥ�Ƽ� ����  ��Ӹ��ϼܵ�ͼ��
	 * ���ڸ��ϼܵ�ͼ�����ѡ������⣺�����������һ����������Ӹ��������ʼ���� �����ݿ���ȡ10 ��������ͼ��
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//���pageNumber
		
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		System.out.println("��ǰҳ����"+pageNumber+"   ����");
		//�Ƽ�ͼ�������
		int searchNumber=6;
		
		RandomBookDao rbd=new RandomBookDao();
		List<RecomendBook> recomendBookList=rbd.getBookInfo(pageNumber, searchNumber);
		
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
		request.setAttribute("pageFlag", "mainPage");
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("recomendBookList", recomendBookList);
		request.setAttribute("bookInfoList", bookInfoList);
		
		request.getRequestDispatcher("main/main.jsp").forward(request, response);
		return;
	}

}
