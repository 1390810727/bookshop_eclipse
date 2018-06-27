package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.DBTools;
import model.Book;
import model.Purchase;
import model.Sb;

/**
 * ͼ���������Ĳ�����dao��
 * @author KUIKUI
 *�У���book������Ӽ�¼����sb��������Ӽ�¼����purchase��������Ӽ�¼
 */
public class AddBookDao {
	
	/** ��book������Ӽ�¼*/
	public  boolean addBook(Book book)
	{
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("insert into book(b_no,name,type,author,price,publish,publish_date) values(?,?,?,?,?,?,?)");
			ps.setString(1, book.getB_no());
			ps.setString(2, book.getName());
			ps.setString(3, book.getType());
			ps.setString(4, book.getAuthor());
			ps.setDouble(5, book.getPrice());
			ps.setString(6, book.getPublish());
			ps.setString(7, book.getPublish_date());
			int n=ps.executeUpdate();
			if(n>0)
			{
				return true;
			}else {
				return false;
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		
		return false;
	}
	
	/**��ָ����s_no��b_no���޸�������������ԭ��������number*/
	public boolean updateSbNumber(String s_no,String b_no, int number)
	{
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("update sb set number=number+? where s_no=?  and b_no=?");
			ps.setInt(1, number);
			ps.setString(2, s_no);
			ps.setString(3, b_no);
			int n=ps.executeUpdate();
			if(n>0)
			{
				return true;
			}else {
				return false;
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		
		return false;
	}
	
	/**��sb������µļ�¼*/
	public boolean addSb(Sb sb)
	{
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("insert into sb (b_no, s_no, number, sell_price, book_info, img_address1,img_address2, img_address3, is_sell) "
					+ " values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, sb.getB_no());
			ps.setString(2, sb.getS_no());
			ps.setInt(3, sb.getNumber());
			ps.setDouble(4, sb.getSell_price());
			ps.setString(5, sb.getBook_info());
			ps.setString(6, sb.getImg_address1());
			ps.setString(7, sb.getImg_address2());
			ps.setString(8, sb.getImg_address3());
			ps.setString(9, sb.getIs_sell());
			int n=ps.executeUpdate();
			if(n>0)
			{
				return true;
			}else {
				return false;
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		
		return false;
	}
	
	/** ���½�����¼�ı� */
	public boolean addPurchase(Purchase purchase)
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("insert into purchase(s_no,b_no,price,number,date) values(?,?,?,?,?)");
			ps.setString(1, purchase.getS_no());
			ps.setString(2, purchase.getB_no());
			ps.setDouble(3, purchase.getPrice());
			ps.setInt(4, purchase.getNumber());
			ps.setString(5, purchase.getDate());
			int n=ps.executeUpdate();
			if(n>0)
			{
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		
		return false;
	}
}
