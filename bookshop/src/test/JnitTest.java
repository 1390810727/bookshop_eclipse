package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import dao.DBTools;

public class JnitTest {
	
	public static void main(String args[])
	{
		System.out.println("ceshi");
		try {
			throw new Exception("测试异常");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常之后");
		}
		
		
	}
	
	public void test3(){
		
	}
	
	@Test
	public void test1()
	{
		System.out.println("ceshi");
		try {
		//连接数据库测试
		Connection con=DBTools.getConnection();
		Statement stat=con.createStatement();
			
			ResultSet rs=stat.executeQuery("select * from user");
			while(rs.next())
			{
				String id=rs.getString(1);
				String password=rs.getString(2);
				System.out.println(id+"|"+password);
				System.out.println("id的长度："+id.length());
				System.out.println("密码的长度："+password.length());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void test2() throws ParseException {
		
	      Calendar now = Calendar.getInstance();  
	        System.out.println("年: " + now.get(Calendar.YEAR));  
	        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
	        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));  
	        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));  
	        System.out.println("分: " + now.get(Calendar.MINUTE));  
	        System.out.println("秒: " + now.get(Calendar.SECOND));  
	        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());  
	        System.out.println(now.getTime());  
	  
	        Date d = new Date();  
	        System.out.println(d);  
	       // SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateNowStr = sdf.format(d);  
	        System.out.println("格式化后的日期：" + dateNowStr);  
	          
	        String str = "2012-1-13 17:26:33";  //要跟上面sdf定义的格式一样  
	        Date today = sdf.parse(str);  
	        System.out.println("字符串转成日期：" + today); 
		
	}
}
