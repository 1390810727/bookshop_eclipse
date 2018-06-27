package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.SellerInfo;

public class SellerSalesDao {
	/**
	 * 获得商家指定日期的 指定  数量的销量信息
	 */
	public List<SellerInfo>  getSaleNumber(String date,int num)
	{
		List<SellerInfo> list=new ArrayList<>();
		//获取当前日期
		
		String sql="select t.s_no,t.b_no,shop.name,book.name,sum(t.number),sum(t.number*t.price)    from shop,sb,order_form t,book" + 
				"	where t.s_no = shop.s_no "
				+ " and t.b_no = book.b_no "
				+ " and t.update_date like ? "
				+ " and t.status = ? " + 
				"   group by t.s_no , t.b_no ,shop.name ,book.name" + 
				"   order by sum(t.number) desc"
				+ " limit 0, ? ";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//连接数据库，做查询，查找排行前十的商家和店铺的信息
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+date+"%");
			ps.setString(2, "待评价");
			ps.setInt(3, num);
			
			rs=ps.executeQuery();
			//获取获得的信息
			while(rs.next())
			{
				String s_no=rs.getString(1);
				String b_no=rs.getString(2);
				String shopName=rs.getString(3);
				String bookName=rs.getString(4);
				int number=rs.getInt(5);
				Double amount=rs.getDouble(6);
				
				SellerInfo sellerInfo=new SellerInfo();
				sellerInfo.setS_no(s_no);
				sellerInfo.setB_no(b_no);
				sellerInfo.setShopName(shopName);
				sellerInfo.setBookName(bookName);
				sellerInfo.setNumber(number);
				sellerInfo.setAmount(amount);
				list.add(sellerInfo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
			
		}
		return list;
	}
	
	public List<SellerInfo>  getSaleAmount(String date,int num)
	{
		List<SellerInfo> list=new ArrayList<>();
		//获取当前日期
		
		String sql="select t.s_no,t.b_no,shop.name,book.name,sum(t.number),sum(t.number*t.price)    from shop,sb,order_form t,book" + 
				"	where t.s_no = shop.s_no "
				+ " and t.b_no = book.b_no "
				+ " and t.update_date like ? "
				+ " and t.status = ? " + 
				"   group by t.s_no , t.b_no ,shop.name ,book.name" + 
				"   order by sum(t.number*t.price) desc"
				+ " limit 0, ? ";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//连接数据库，做查询，查找排行前十的商家和店铺的信息
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+date+"%");
			ps.setString(2, "待评价");
			ps.setInt(3, num);
			
			rs=ps.executeQuery();
			//获取获得的信息
			while(rs.next())
			{
				String s_no=rs.getString(1);
				String b_no=rs.getString(2);
				String shopName=rs.getString(3);
				String bookName=rs.getString(4);
				int number=rs.getInt(5);
				Double amount=rs.getDouble(6);
				
				SellerInfo sellerInfo=new SellerInfo();
				sellerInfo.setS_no(s_no);
				sellerInfo.setB_no(b_no);
				sellerInfo.setShopName(shopName);
				sellerInfo.setBookName(bookName);
				sellerInfo.setNumber(number);
				sellerInfo.setAmount(amount);
				list.add(sellerInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		return list;
	}
}
