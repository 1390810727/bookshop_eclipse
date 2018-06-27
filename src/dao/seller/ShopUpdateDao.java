package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBTools;
import model.Shop;

/**
 * 修改店铺信息得Dao层
 * @author KUIKUI
 *
 */
public class ShopUpdateDao {
	/**
	 * 更新店铺得信息
	 * 如果更新成功，则返回true,否则返回false
	 */
	public boolean updateShopInfo(Shop shop)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			
			con=DBTools.getConnection();
			ps=con.prepareStatement("update shop set name=?, province=?, city=?, country=?, street=?, post_code=?, introduction=?, img_address=?"
					+ " where s_no=?");
			ps.setString(1, shop.getShopName());
			ps.setString(2, shop.getProvince());
			ps.setString(3, shop.getCity());
			ps.setString(4, shop.getCountry());
			ps.setString(5, shop.getStreet());
			ps.setString(6, shop.getPostCode());
			ps.setString(7, shop.getIntroduction());
			ps.setString(8, shop.getImgAddress());
			ps.setString(9, shop.getS_no());
			
			int n=ps.executeUpdate();
			System.out.println("被修改的行数"+n );
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
			DBTools.close(rs);
		}
		return false;
	}
}
