package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DBTools;

/**
 * 连接数据库，删除指定的店铺的所有信息，谨慎操作
 * @author KUIKUI
 *
 */
public class ShopDeleteDao {
	
	/**
	 * 删除指定店铺号的店铺的所有信息，涉及到两个表,shop表和 sb表，所以必须是该数据库删除操作必须是原子性的
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
			
			//如果操作成功，则返回true,否则返回false
			
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
