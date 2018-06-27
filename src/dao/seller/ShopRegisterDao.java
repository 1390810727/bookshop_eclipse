package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import dao.DBTools;
import model.Shop;

/**
 * ע����̵ķ�װ������Ҫ�����������ݿ⣬�����ݿ⽻������
 * @author KUIKUI
 *
 */
public class ShopRegisterDao {
	
	/**
	 * ��ע��ĵ�����Ϣ���µ����ݿ�,����ɹ��򷵻�true,���ʧ�ܣ��򷵻�false;
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
			
			//��������ݣ��򷵻�false;���򷵻�true;
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
