package dao.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.BookInfo;
import model.RecomendBook;

public class SearchBookDao {

	public List<RecomendBook> searchByName(String search_content,int page,int number) {
		List<RecomendBook> list=new ArrayList<>();
		BookInfo bookInfo=null;
		RecomendBook recomendBook=null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		
		String sql2="select * from book,sb,shop"
				+ " where sb.b_no=book.b_no "
				+ " and sb.s_no=shop.s_no "
				+ " and book.name like ? "
				+ " limit ?,?";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql2);
			ps.setString(1, "%"+search_content+"%");
			ps.setInt(2, page);
			ps.setInt(3, number);
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				bookInfo=new BookInfo();
				recomendBook=new RecomendBook();
				
				bookInfo.setB_no(rs.getString("sb.b_no"));
				bookInfo.setName(rs.getString("book.name"));
				bookInfo.setType(rs.getString("book.type"));
				bookInfo.setAuthor(rs.getString("author"));
				bookInfo.setPrice(rs.getDouble("book.price"));
				bookInfo.setSell_price(rs.getDouble("sb.sell_price"));
				bookInfo.setNumber(rs.getInt("sb.number"));
				bookInfo.setPublish(rs.getString("book.publish"));
				bookInfo.setPublish_date(rs.getString("book.publish_date"));
				bookInfo.setBook_info(rs.getString("sb.book_info"));
				bookInfo.setImgAddress1(rs.getString("sb.img_address1"));
				bookInfo.setImgAddress2(rs.getString("sb.img_address2"));
				bookInfo.setImgAddress3(rs.getString("sb.img_address3"));
				bookInfo.setS_no(rs.getString("sb.s_no"));
				
				recomendBook.setBookInfo(bookInfo);
				recomendBook.setShopName(rs.getString("shop.name"));
				
				list.add(recomendBook);
			}
			
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		return list;
	}
	
	public List<RecomendBook> searchByType(String search_content,int page,int number) {
		List<RecomendBook> list=new ArrayList<>();
		BookInfo bookInfo=null;
		RecomendBook recomendBook=null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		
		String sql2="select * from book,sb,shop"
				+ " where sb.b_no=book.b_no "
				+ " and sb.s_no=shop.s_no "
				+ " and book.type like ? "
				+ " limit ?,?";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql2);
			ps.setString(1, "%"+search_content+"%");
			ps.setInt(2, page);
			ps.setInt(3, number);
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				bookInfo=new BookInfo();
				recomendBook=new RecomendBook();
				
				bookInfo.setB_no(rs.getString("sb.b_no"));
				bookInfo.setName(rs.getString("book.name"));
				bookInfo.setType(rs.getString("book.type"));
				bookInfo.setAuthor(rs.getString("author"));
				bookInfo.setPrice(rs.getDouble("book.price"));
				bookInfo.setSell_price(rs.getDouble("sb.sell_price"));
				bookInfo.setNumber(rs.getInt("sb.number"));
				bookInfo.setPublish(rs.getString("book.publish"));
				bookInfo.setPublish_date(rs.getString("book.publish_date"));
				bookInfo.setBook_info(rs.getString("sb.book_info"));
				bookInfo.setImgAddress1(rs.getString("sb.img_address1"));
				bookInfo.setImgAddress2(rs.getString("sb.img_address2"));
				bookInfo.setImgAddress3(rs.getString("sb.img_address3"));
				bookInfo.setS_no(rs.getString("sb.s_no"));
				
				recomendBook.setBookInfo(bookInfo);
				recomendBook.setShopName(rs.getString("shop.name"));
				
				list.add(recomendBook);
			}
			
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		return list;
	}
	
}
