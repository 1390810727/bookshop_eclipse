package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.Shop;

/**
 * �����ݿ����ӣ��Ӹ����̼�id��������ȡ��ӵ�еĵ��̼���
 * @author KUIKUI
 *
 */
public class ShopListDao {
	
	/**
	 * �Ӹ����̼�id��������ȡ��ӵ�еĵ��̼���
	 * s_idΪ �̼�id���ַ���
	 */
	public List<Shop> getShopList(String s_id)
	{
		List<Shop> shopList=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from shop where s_id=?");
			ps.setString(1, s_id);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Shop shop=new Shop();
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
				
				/**���´�����shopʵ����ӵ�������*/
				
				shopList.add(shop);
			}
			
			/**��shopList���س�ȥ */
			return shopList;
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
