package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBTools;
import dao.RegisterDaoInterface;
import model.Seller;

public class SellerRegisterDao implements RegisterDaoInterface {

		
	/**
	 * �������ݿ⣬��seller�浽���ݿ���
	 */
	public boolean doRegister(Seller seller) {
		
	
		Connection con=null;
		PreparedStatement ps=null;
	//	ResultSet rs=null;
		//�������ݿ�
		try {
			
			 con=DBTools.getConnection();
			ps= con.prepareStatement("insert into seller values(?,?,?,?,?)");
			ps.setString(1, seller.getId());
			ps.setString(2, seller.getName());
			ps.setString(3, seller.getPassword());
			ps.setString(4, seller.getGender());
			ps.setString(5, seller.getTel());
			System.out.println("nameΪ��"+seller.getName()+"   �Ա�Ϊ:"+seller.getGender());
			ps.executeUpdate();
			
		}catch(Exception e )
		{
			e.printStackTrace();
			return false;
			
		}
		finally {
			DBTools.close(con);
			DBTools.close(ps);
		//	DBTools.close(rs);
		}
		return true;
		
	}

	/**
	 * ajax������id,������ݿ����Ƿ��Ѿ����ڸ��˺ţ���������򷵻�false;
	 * ����������򷵻�true;
	 */
	public boolean checkId(String id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from seller where s_id=?");
			ps.setString(1, id);
			rs=ps.executeQuery();
			
			//��������ݣ��򷵻�false;���򷵻�true;
			if(rs.next())
			{
				return false;
			}else {
				return true;
			}
			
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
			return false;
	}

	@Override
	public boolean checkShopName(String shopName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	


}
