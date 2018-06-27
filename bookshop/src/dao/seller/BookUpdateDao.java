package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.DBTools;

public class BookUpdateDao {
	
	
	/**
	 * ��ͼ������¼ܲ���
	 * @param s_no  ���̵�id		
	 * @param b_no	ͼ��ı��
	 * @param isSell �����ϼܻ����¼�
	 * @return
	 */
	public boolean updateIsSell(String s_no,String b_no,String isSell)
	{
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("update sb set is_sell=? where s_no=?  and b_no=?");
			ps.setString(1, isSell);
			ps.setString(2, s_no);
			ps.setString(3, b_no);
			int n=ps.executeUpdate();
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
		}
		
		return false;
		
		
	}

	
	public boolean changeSellPrice(String s_no,String b_no,double newSellPrice) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("update sb set sell_price=? where s_no=?  and b_no=?");
			ps.setDouble(1, newSellPrice);
			ps.setString(2, s_no);
			ps.setString(3, b_no);
			int n=ps.executeUpdate();
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
		}
		
		return false;
		
	}
}
