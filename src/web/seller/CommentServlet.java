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
		
		//获得店铺编号
		String s_no=(String)session.getAttribute("s_no");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		//请求到评论页,获取该店铺的所有图书的评论信息
		if("/commentList".equals(path))
		{
			CommentListDao cld=new CommentListDao();
			List<CommentInfo> commentInfoList=cld.getCommentList(s_no);
			
			//将comment列表信息存到request里，然后转发
			request.setAttribute("commentInfoList", commentInfoList);
			//转发到评论页面
			request.getRequestDispatcher("seller/book_comment.jsp").forward(request, response);
			
		}
		
	}
	

}
