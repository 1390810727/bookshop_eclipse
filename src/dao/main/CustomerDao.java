package dao.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.DBTools;
import model.Address;

public class CustomerDao {

	
	
	/**
	 * 新增收货地址
	 * @param address收货地址的封装类
	 * @return  是否成功
	 */
	public boolean addAddress(Address address) {
		Connection con=null;
		PreparedStatement ps=null;
		  try {
			  con=DBTools.getConnection();
			  ps=con.prepareStatement("insert into address(c_id,name,province,city,country,street,post_code,tell_no)"
			  		+ " values(?,?,?,?,?,?,?,?)");
			  ps.setString(1, address.getC_id());
			  ps.setString(2, address.getName());
			  ps.setString(3, address.getProvince());
			  ps.setString(4, address.getCity());
			  ps.setString(5, address.getCountry());
			  ps.setString(6, address.getStreet());
			  ps.setString(7, address.getPost_code());
			  ps.setString(8, address.getTell_no());
			  
			  int n=ps.executeUpdate();
			  if(n>0) {
				  
				  return true;
			  }else {
				  return false;
			  }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }finally {
			  
			  DBTools.close(ps);
			  DBTools.close(con);
		  }
		
		
		
		return false;
	}
}
