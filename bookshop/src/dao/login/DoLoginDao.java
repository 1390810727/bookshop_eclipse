package dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.DBTools;
import model.User;

public class DoLoginDao {
	
	
	/**
	 * ϵͳ����Ա��½��Dao�㣬�������ݿ��������Ƿ���ȷ
	 * @param user
	 * @return	����ɹ��򷵻��û�id,���ʧ���򷵻�null
	 */
	public String doLoginAdmin(User user)
	{
		String id=user.getId();
		String  password =user.getPassword();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//�������ݿ�
		try {
			
			 con=DBTools.getConnection();
			ps= con.prepareStatement("select * from user where id=? and password=?");
			ps.setString(1, id);
			ps.setString(2,password);
			System.out.println("idΪ��"+id+"   ����Ϊ:"+password);
			rs=ps.executeQuery();
		
			System.out.println("rs�Ƿ�Ϊ��"+(rs==null));
			//���ȡ��ֵ���򷵻ص�½���û���
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
	 * ���̼ҵ�½��Dao��
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
		//�������ݿ�
		try {
			
			 con=DBTools.getConnection();
			ps= con.prepareStatement("select * from seller where s_id=? and password=?");
			ps.setString(1, id);
			ps.setString(2,password);
			System.out.println("idΪ��"+id+"   ����Ϊ:"+password);
			rs=ps.executeQuery();
		
			System.out.println("rs�Ƿ�Ϊ��"+(rs==null));
			//���ȡ��ֵ���򷵻ص�½���û���
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
	 * ����id�����ݿ��л�ȡseller ��Name
	 * @return
	 */
	public String getSellerName(String id)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null;
		//�������ݿ�
		try {
			
			 con=DBTools.getConnection();
			ps= con.prepareStatement("select name from seller where s_id=?");
			ps.setString(1, id);
			rs=ps.executeQuery();
		
		//	System.out.println("rs�Ƿ�Ϊ��"+(rs==null));
			//���ȡ��ֵ���򷵻ص�½���û���
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
	 * ���˿͵�½��dao��
	 * @param user  ���ڴ洢id ��  ����� ��װ��
	 * @return  �˿͵�id
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
	 * ���ݹ˿͵�id��� �˿͵�����
	 * @param c_id  �˿͵�id
	 * @return  �˿͵�����
	 */
	public String getCustomerName(String c_id)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null;
		//�������ݿ�
		try {
			
			con=DBTools.getConnection();
			ps= con.prepareStatement("select name from customer where c_id=?");
			ps.setString(1, c_id);
			rs=ps.executeQuery();
		//	System.out.println("rs�Ƿ�Ϊ��"+(rs==null));
			//���ȡ��ֵ���򷵻ص�½���û���
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
