package dao.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.ShopCard;
import model.ShopCardBook;

/**
 * ���ﳵ��dao��
 * @author KUIKUI
 *
 */
public class ShopCardDao {
	/**
	 * ���ﳵ�����ͼ��
	 * �ȼ���ͼ���Ƿ��Ѿ����ڣ����ͼ���Ѿ����ڣ���ֻ���޸��������ɣ�����ֱ�����ͼ�鼴��;
	 * @param shopCard
	 * @return
	 */
	public boolean addBook(ShopCard shopCard)
	{
		//����й���Ϣ
		String c_id=shopCard.getC_id();
		String s_no=shopCard.getS_no();
		String b_no=shopCard.getB_no();
		int number=shopCard.getNumber();
		//�����ͼ���Ƿ��Ѿ������ݿ��д��ڣ��������
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from shop_card "
					+ " where c_id = ? "
					+ " and s_no = ?"
					+ " and b_no = ?");
			ps.setString(1, c_id);
			ps.setString(2, s_no);
			ps.setString(3, b_no);
			
			rs=ps.executeQuery();
			
			//���rsΪ�գ���������δ����ӽ����ݿ⣬ֱ�ӽ��ü�¼��ӽ�shopcard���м���
			if(!rs.next())
			{
				ps=con.prepareStatement("insert into shop_card(c_id,s_no,b_no,number)  values(?,?,?,?)");
				ps.setString(1, c_id);
				ps.setString(2, s_no);
				ps.setString(3, b_no);
				ps.setInt(4, number);
				
				int n=ps.executeUpdate();
				if(n>0) {
					return true;
				}else {
					return false;
				}
			}else {
				ps=con.prepareStatement("update shop_card set number=number+? where c_id=?"
						+ " and s_no=? "
						+ " and b_no=? ");
				ps.setInt(1, number);
				ps.setString(2, c_id);
				ps.setString(3, s_no);
				ps.setString(4, b_no);
				
				int n=ps.executeUpdate();
				if(n>0) {
					return true;
				}else {
					return false;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		return false;
	}
	
	/**
	 * ��ʾ���ﳵ�е�ͼ���б���Ϣ
	 * @param shopCardBook
	 * @return ShopCardBook���͵ļ���
	 */
	public List<ShopCardBook> loadBook(String c_id) {
		
		List<ShopCardBook> scbList=new ArrayList<>();
		ShopCardBook shopCardBook=null;
		ShopCard shopCard=null;
		//�����������ݿ��л��ͼ�����Ϣ
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select s_no,b_no,number from shop_card where c_id = ?";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select sc.s_no,sc.b_no,book.name,shop.name,sb.sell_price,sc.number,sb.img_address1"
					+ " from book,shop,sb,("+sql+") as sc"
					+ " where sc.s_no = sb.s_no"
					+ " and sc.b_no = sb.b_no"
					+ " and sb.b_no = book.b_no"
					+ " and sb.s_no = shop.s_no");
			ps.setString(1, c_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				shopCardBook=new ShopCardBook();
				shopCard=new ShopCard();
				shopCard.setS_no(rs.getString("sc.s_no"));
				shopCard.setB_no(rs.getString("sc.b_no"));
				shopCard.setNumber(rs.getInt("sc.number"));
				shopCard.setC_id(c_id);
				shopCardBook.setShopCard(shopCard);
				shopCardBook.setBookName(rs.getString("book.name"));
				shopCardBook.setShopName(rs.getString("shop.name"));
				shopCardBook.setPrice(rs.getDouble("sb.sell_price"));
				shopCardBook.setImgAddress1(rs.getString("sb.img_address1"));
				
				scbList.add(shopCardBook);
			}
			
			return scbList;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		return null;
	}
	
	public boolean changeNumber(String c_id,String s_no,String b_no,int number) {
		Connection con=null;
		PreparedStatement ps=null;
		String sql="update shop_card "
				+ " set number = ? "
				+ " where c_id = ? "
				+ " and s_no = ? "
				+ " and b_no = ?";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, number);
			ps.setString(2, c_id);
			ps.setString(3, s_no);
			ps.setString(4, b_no);
			int n=ps.executeUpdate();
			System.out.println("n��ֵΪ��"+n);
			if(n>0) {
				
				return true;
			}else {
				return false;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		return false;
	}
	
	public boolean deleteShopCard(String c_id,String s_no,String b_no) {
		Connection con=null;
		PreparedStatement ps=null;
		String sql="delete from shop_card "
				+ " where c_id = ? "
				+ " and s_no = ? "
				+ " and b_no = ? ";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, c_id);
			ps.setString(2, s_no);
			ps.setString(3, b_no);
			int n=ps.executeUpdate();
			if(n>0) {
				
				return true;
			}else {
				return false;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		return false;
	}
}
