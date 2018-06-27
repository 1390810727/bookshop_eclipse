package web.seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.seller.CommentListDao;
import model.CommentInfo;

public class CommentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session=request.getSession();
		PrintWriter pw=response.getWriter();
		
		//��õ��̱��
		String s_no=(String)session.getAttribute("s_no");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		//��������ҳ,��ȡ�õ��̵�����ͼ���������Ϣ
		if("/commentList".equals(path))
		{
			CommentListDao cld=new CommentListDao();
			List<CommentInfo> commentInfoList=cld.getCommentList(s_no);
			
			//��comment�б���Ϣ�浽request�Ȼ��ת��
			request.setAttribute("commentInfoList", commentInfoList);
			//ת��������ҳ��
			request.getRequestDispatcher("seller/book_comment.jsp").forward(request, response);
			
		}
		
	}
	

}
