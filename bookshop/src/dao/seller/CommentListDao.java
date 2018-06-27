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
 * ��ָ�����̺�����ȡ����ͼ���������Ϣ
 * @author KUIKUI
 *
 */
public class CommentListDao {
	/**
	 * �����ݿ��л��ָ�����̺ţ�ͼ���ŵ�������Ϣ,
	 * @param s_no ���̺�
	 * @param b_no ͼ����
	 * @return	CommentInfo���͵ļ���
	 */
	public List<CommentInfo> getCommentList(String s_no){
		List<CommentInfo> commentList=new ArrayList<>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//���Ȼ��bookInfo����Ϣ������
		BookListDao bld=new BookListDao();
		
		//����˸õ��̵�����ͼ���б�
		List<BookInfo> bookList=bld.bookList(s_no);
		
		//���Ž��ж�ÿ��ͼ�������Ȼ��ÿ��ͼ��������б��ȡ��
		CommentInfo commentInfo=null;
		for(BookInfo bookInfo:bookList)
		{
			commentInfo=new CommentInfo();
			commentInfo.setBookInfo(bookInfo);
			
			List<Comment> comList=new ArrayList<>();
			String b_no=bookInfo.getB_no();
			//��ȡ��b_no,s_noָ����������Ϣ
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
			
		}//for������
		return commentList;
	}
}
