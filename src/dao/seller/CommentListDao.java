package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.BookInfo;
import model.CommentInfo;

/**
 * 从指定店铺号中提取所有图书的评论信息
 * @author KUIKUI
 *
 */
public class CommentListDao {
	/**
	 * 从数据库中获得指定店铺号，图书编号的评论信息,
	 * @param s_no 店铺号
	 * @param b_no 图书编号
	 * @return	CommentInfo类型的集合
	 */
	public List<CommentInfo> getCommentList(String s_no){
		List<CommentInfo> commentList=new ArrayList<>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//首先获得bookInfo的信息，利用
		BookListDao bld=new BookListDao();
		
		//获得了该店铺的所有图书列表
		List<BookInfo> bookList=bld.bookList(s_no);
		
		//接着进行对每个图书遍历，然后将每个图书的评论列表获取到
		CommentInfo commentInfo=null;
		for(BookInfo bookInfo:bookList)
		{
			commentInfo=new CommentInfo();
			commentInfo.setBookInfo(bookInfo);
			
			List<Comment> comList=new ArrayList<>();
			String b_no=bookInfo.getB_no();
			//获取由b_no,s_no指定的评论信息
			try {
				con=DBTools.getConnection();
				ps=con.prepareStatement("select * from customer,comment where customer.c_id=comment.c_id "
						+ "and b_no=?"
						+ "and s_no=?"
						+ " order by date desc");
				ps.setString(1, b_no);
				ps.setString(2, s_no);
				rs=ps.executeQuery();
				while(rs.next())
				{
					Comment comment=new Comment();
					comment.setC_id(rs.getString("c_id"));
					comment.setC_name(rs.getString("name"));
					comment.setComment_info(rs.getString("comment_info"));
					comment.setB_no(rs.getString("b_no"));
					comment.setS_no(s_no);
					comment.setDateTime(rs.getString("date"));
					comList.add(comment);
				}
				
				commentInfo.setCommentList(comList);
				commentList.add(commentInfo);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally {
				
				DBTools.close(con);
				DBTools.close(ps);
				DBTools.close(rs);
			}
			
		}//for语句结束
		return commentList;
	}
}
