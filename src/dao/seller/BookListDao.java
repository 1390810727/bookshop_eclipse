package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.BookInfo;

/**
 * �г��������̵�����ͼ��
 * @author KUIKUI
 *
 */
public class BookListDao {
	
	/**�г�δ�ϼܵ�ͼ���б�*/
	public List<BookInfo> unSellBookList(String s_no){
		List<BookInfo> bookList=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//��ѯ���̺�s_no������δ�ϼܵ�ͼ��
		String sql="select * from book,sb where sb.b_no=book.b_no and s_no=?  and is_sell='��' ";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, s_no);
			rs=ps.executeQuery();
			while(rs.next())
			{
				BookInfo bookInfo=new BookInfo();
				
				bookInfo.setB_no(rs.getString("b_no"));
				bookInfo.setName(rs.getString("name"));
				bookInfo.setType(rs.getString("type"));
				bookInfo.setAuthor(rs.getString("author"));
				bookInfo.setPrice(Double.parseDouble(rs.getString("price")));
				bookInfo.setSell_price(Double.parseDouble(rs.getString("sell_price")));
				bookInfo.setNumber(Integer.parseInt(rs.getString("number")));
				bookInfo.setPublish(rs.getString("publish"));
				bookInfo.setPublish_date(rs.getString("publish_date"));
				bookInfo.setBook_info(rs.getString("book_info"));
				bookInfo.setImgAddress1(rs.getString("img_address1"));
				bookInfo.setImgAddress2(rs.getString("img_address2"));
				bookInfo.setImgAddress3(rs.getString("img_address3"));
				bookInfo.setIsSell(rs.getString("is_sell"));
				bookInfo.setS_no(s_no);
				
				bookList.add(bookInfo);
				
			}
			
			return bookList;
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		
		return null;
		
	}
	
	/**�г����ϼܵ�ͼ���б�*/
	public List<BookInfo> sellBookList(String s_no){
		List<BookInfo> bookList=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//��ѯ���̺�s_no������δ�ϼܵ�ͼ��
		String sql="select * from book,sb where sb.b_no=book.b_no and s_no=?  and is_sell='��' ";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, s_no);
			rs=ps.executeQuery();
			while(rs.next())
			{
				BookInfo bookInfo=new BookInfo();
				
				bookInfo.setB_no(rs.getString("b_no"));
				bookInfo.setName(rs.getString("name"));
				bookInfo.setType(rs.getString("type"));
				bookInfo.setAuthor(rs.getString("author"));
				bookInfo.setPrice(Double.parseDouble(rs.getString("price")));
				bookInfo.setSell_price(Double.parseDouble(rs.getString("sell_price")));
				bookInfo.setNumber(Integer.parseInt(rs.getString("number")));
				bookInfo.setPublish(rs.getString("publish"));
				bookInfo.setPublish_date(rs.getString("publish_date"));
				bookInfo.setBook_info(rs.getString("book_info"));
				bookInfo.setImgAddress1(rs.getString("img_address1"));
				bookInfo.setImgAddress2(rs.getString("img_address2"));
				bookInfo.setImgAddress3(rs.getString("img_address3"));
				bookInfo.setIsSell(rs.getString("is_sell"));
				bookInfo.setS_no(s_no);
				
				bookList.add(bookInfo);
				
			}
			
			return bookList;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		return null;
		
	}
	public List<BookInfo> bookList(String s_no){
		List<BookInfo> bookList=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//��ѯ���̺�s_no������δ�ϼܵ�ͼ��
		String sql="select * from book,sb where sb.b_no=book.b_no and s_no=? ";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, s_no);
			rs=ps.executeQuery();
			while(rs.next())
			{
				BookInfo bookInfo=new BookInfo();
				
				bookInfo.setB_no(rs.getString("b_no"));
				bookInfo.setName(rs.getString("name"));
				bookInfo.setType(rs.getString("type"));
				bookInfo.setAuthor(rs.getString("author"));
				bookInfo.setPrice(Double.parseDouble(rs.getString("price")));
				bookInfo.setSell_price(Double.parseDouble(rs.getString("sell_price")));
				bookInfo.setNumber(Integer.parseInt(rs.getString("number")));
				bookInfo.setPublish(rs.getString("publish"));
				bookInfo.setPublish_date(rs.getString("publish_date"));
				bookInfo.setBook_info(rs.getString("book_info"));
				bookInfo.setImgAddress1(rs.getString("img_address1"));
				bookInfo.setImgAddress2(rs.getString("img_address2"));
				bookInfo.setImgAddress3(rs.getString("img_address3"));
				bookInfo.setIsSell(rs.getString("is_sell"));
				bookInfo.setS_no(s_no);
				
				bookList.add(bookInfo);
				
			}
			
			return bookList;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		return null;
		
	}
}
