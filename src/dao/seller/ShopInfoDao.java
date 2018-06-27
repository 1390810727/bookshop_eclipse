package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBTools;
import model.Shop;

/**
 * 获取指定的单个店铺信息
 * @author KUIKUI
 *
 */
public class ShopInfoDao {
	/**
	 * 从给定的商家id和给定的店铺号获取单个店铺的所有信息
	 * @param s_id 商家id
	 * @param s_no 店铺no
	 */
	public Shop getShopInfo(String s_id,String s_no) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from shop where s_id=? and s_no=?");
			ps.setString(1, s_id);
			ps.setString(2, s_no);
			rs=ps.executeQuery();
			Shop shop=new Shop();
			while(rs.next())
			{
				shop.setS_no(rs.getString("s_no"));
				shop.setShopName(rs.getString("name"));
				shop.setProvince(rs.getString("province"));
				shop.setCity(rs.getString("city"));
				shop.setCountry(rs.getString("country"));
				shop.setStreet(rs.getString("street"));
				shop.setPostCode(rs.getString("post_code"));
				shop.setIntroduction(rs.getString("introduction"));
				shop.setImgAddress(rs.getString("img_address"));
				shop.setS_id(rs.getString("s_id"));
			}
			
			/**将shop返回出去 */
			return shop;
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
