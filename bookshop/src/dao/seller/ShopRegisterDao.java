package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import dao.DBTools;
import model.Shop;

/**
 * 注册店铺的封装来，主要用来连接数据库，与数据库交换数据
 * @author KUIKUI
 *
 */
public class ShopRegisterDao {
	
	/**
	 * 将注册的店铺信息更新到数据库,如果成功则返回true,如果失败，则返回false;
	 * @param shop
	 */
	public boolean doShopRegister(Shop shop)
	{
		
		
		
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("insert into shop values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, shop.getS_no());
			ps.setString(2, shop.getShopName());
			ps.setString(3, shop.getProvince());
			ps.setString(4, shop.getCity());
			ps.setString(5, shop.getCountry());
			ps.setString(6, shop.getStreet());
			ps.setString(7, shop.getPostCode());
			ps.setString(8, shop.getIntroduction());
			ps.setString(9, shop.getImgAddress());
			ps.setString(10, shop.getS_id());
			int n=ps.executeUpdate();
			
			//如果有数据，则返回false;否则返回true;
			if(n>=1)
			{
				return true;
			}
			
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		finally {
			DBTools.close(con);
			DBTools.close(ps);
			
		}
			return false;
	}
}
