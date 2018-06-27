package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBTools;


public class BookInfoDao {

	/**
	 * ����ָ��ͼ����book�����Ƿ��Ѿ�����
	 * ����false�������ڣ�����true�������
	 */
	public boolean isExistInBook(String b_no)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from book where b_no=? ");
			ps.setString(1, b_no);
			rs=ps.executeQuery();
			//�������
			if( rs.next())
			{
				return true;
			}
			else {
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
	
	
	/**
	 * ���ָ��ͼ����ָ������sb�����Ƿ��Ѿ����ڣ���������򷵻�true,���򷵻�false
	 * b_noΪͼ���ţ�s_noΪ���̱��
	 */
	public boolean isExistInSb(String b_no,String s_no)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from sb where b_no=? and s_no=?");
			ps.setString(1, b_no);
			ps.setString(2, s_no);
			rs=ps.executeQuery();
			//�������
			if(rs.next())
			{
				return true;
			}
			else {
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
