package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBTools;


public class BookInfoDao {

	/**
	 * 检验指定图书在book表里是否已经存在
	 * 返回false代表不存在，返回true代表存在
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
			//如果存在
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
	 * 检测指定图书在指定店铺sb表里是否已经存在，如果存在则返回true,否则返回false
	 * b_no为图书编号，s_no为店铺编号
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
			//如果存在
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
