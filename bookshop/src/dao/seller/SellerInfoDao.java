package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBTools;
import model.Seller;
import model.Shop;

/**
 * �������ݿ⣬��ȡָ���̼ҵ���Ϣ��������Ϣ���з�װ
 * @author KUIKUI
 *
 */
public class SellerInfoDao {
	
	public Seller getSellerInfo(String s_id)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from seller where s_id=? ");
			ps.setString(1, s_id);
			rs=ps.executeQuery();
			Seller seller=new Seller();
			while(rs.next())
			{
				seller.setId(rs.getString("s_id"));
				seller.setName(rs.getString("name"));
				seller.setPassword(rs.getString("password"));
				seller.setGender(rs.getString("gender"));
				seller.setTel(rs.getString("tell_no"));
			}
			
			return seller;
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
