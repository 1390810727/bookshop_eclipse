package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DBTools;

/**
 * �������ݿ⣬ɾ��ָ���ĵ��̵�������Ϣ����������
 * @author KUIKUI
 *
 */
public class ShopDeleteDao {
	
	/**
	 * ɾ��ָ�����̺ŵĵ��̵�������Ϣ���漰��������,shop��� sb�����Ա����Ǹ����ݿ�ɾ������������ԭ���Ե�
	 * @param s_no
	 * @return
	 */
	public boolean deleteShop(String s_no)
	{
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		
		
		try {
			
			con=DBTools.getConnection();
			con.setAutoCommit(false);
			ps=con.prepareStatement("delete from shop where s_no=?");
			ps1=con.prepareStatement("delete from sb where s_no=?");
			ps.setString(1, s_no);
			ps1.setString(1, s_no);
			
			int n=ps.executeUpdate();
			int m=ps1.executeUpdate();
			
			
			
			con.commit();
			return true;
			
			
		/*	ps.addBatch("delete from sb where s_no=?");
			ps.setString(1, s_no);
			
			int[] n=ps.executeBatch();
			
			boolean queryOK=true;
			for(int i=0;i<n.length;i++)
			{
				if(n[i]<=0)
				{
					queryOK=false;
				}
				
			}
			
			if(queryOK)
			{
				con.commit();
				return true;
			}else {
				con.rollback();
				return false;
			}*/
			
			//��������ɹ����򷵻�true,���򷵻�false
			
		}catch(Exception e)
		{
			try {
				con.rollback();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(ps1);
		}
		return false;
		
	}
}
