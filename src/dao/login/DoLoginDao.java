package dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.DBTools;
import model.User;

public class DoLoginDao {
	
	
	/**
	 * 系统管理员登陆的Dao层，连接数据库检查数据是否正确
	 * @param user
	 * @return	如果成功则返回用户id,如果失败则返回null
	 */
	public String doLoginAdmin(User user)
	{
		String id=user.getId();
		String  password =user.getPassword();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//连接数据库
		try {
			
			 con=DBTools.getConnection();
			ps= con.prepareStatement("select * from user where id=? and password=?");
			ps.setString(1, id);
			ps.setString(2,password);
			System.out.println("id为："+id+"   密码为:"+password);
			rs=ps.executeQuery();
		
			System.out.println("rs是否为空"+(rs==null));
			//如果取到值，则返回登陆的用户名
			if(rs.next())
			{
				return id;
			}
			else {
				System.out.println("rs:"+rs);
				return null;
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
		return null;
	}
	
	/**
	 * 做商家登陆的Dao层
	 * @param user
	 * @return
	 */
	public String doLoginSeller(User user)
	{
		String id=user.getId();
		String  password =user.getPassword();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//连接数据库
		try {
			
			 con=DBTools.getConnection();
			ps= con.prepareStatement("select * from seller where s_id=? and password=?");
			ps.setString(1, id);
			ps.setString(2,password);
			System.out.println("id为："+id+"   密码为:"+password);
			rs=ps.executeQuery();
		
			System.out.println("rs是否为空"+(rs==null));
			//如果取到值，则返回登陆的用户名
			if(rs.next())
			{
				return id;
			}
			else {
				System.out.println("rs:"+rs);
				return null;
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
		return null;
	}
	
	/**
	 * 根据id从数据库中获取seller 的Name
	 * @return
	 */
	public String getSellerName(String id)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null;
		//连接数据库
		try {
			
			 con=DBTools.getConnection();
			ps= con.prepareStatement("select name from seller where s_id=?");
			ps.setString(1, id);
			rs=ps.executeQuery();
		
		//	System.out.println("rs是否为空"+(rs==null));
			//如果取到值，则返回登陆的用户名
			while(rs.next())
			{
				name= rs.getString(1);
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
		return name;
		
	
	}
	
	/**
	 * 做顾客登陆的dao层
	 * @param user  用于存储id 和  密码的 封装类
	 * @return  顾客的id
	 */
	public String doLoginCustomer(User user) {
		String c_id=user.getId();
		String password=user.getPassword();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			
			ps=con.prepareStatement("select c_id,name from customer where c_id=?"
					+ " and password=?");
			
			ps.setString(1, c_id);
			ps.setString(2, password);
			rs=ps.executeQuery();
			 
			if(rs.next())
			{
				return c_id;
			}
			else {
				return null;
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(rs);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		return null;
	}
	
	/**
	 * 根据顾客的id获得 顾客的姓名
	 * @param c_id  顾客的id
	 * @return  顾客的姓名
	 */
	public String getCustomerName(String c_id)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null;
		//连接数据库
		try {
			
			con=DBTools.getConnection();
			ps= con.prepareStatement("select name from customer where c_id=?");
			ps.setString(1, c_id);
			rs=ps.executeQuery();
		//	System.out.println("rs是否为空"+(rs==null));
			//如果取到值，则返回登陆的用户名
			while(rs.next())
			{
				name= rs.getString(1);
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
		return name;
	}
	
}
