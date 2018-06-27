package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.SaleAmount;
import model.SaleNumber;

/**
 * 连接数据库，处理图书销量或销售额的信息
 * @author KUIKUI
 *
 */
public class BookSaleDao {
	
	/**
	 * 指定店铺，指定日期进行查询销售额的信息，返回销售额的列表
	 * @param s_no
	 * @param date
	 * @return
	 */
	public List<SaleAmount> saleAmountSearch(String s_no,String date)
	{
		List<SaleAmount> saleList=new ArrayList<>();
		SaleAmount saleAmount=null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		/*String sq="select * from order_form,book,sb where"
				+ " order_form.b_no=book.b_no "
				+ " and order_form.s_no = sb.s_no "
				+ " and order_form.s_no = ?"
				+ " and order_form.status = ? "
				+ " and order_form.update_date like ? ";
		*/
		String sql2="(select order_form.b_no,order_form.s_no,sum(order_form.number*(order_form.price-purchase.price)) as profit, sum(order_form.number*order_form.price) as amount from order_form, purchase"
				+ " where order_form.s_no = purchase.s_no "
				+ " and order_form.b_no = purchase.b_no "
				+ " and order_form.status <> ? "
				+ " and order_form.update_date like ? "
				+ " and order_form.s_no = ?"
				+ " group by order_form.b_no,order_form.s_no) as o";
		
		String sql="select * from book,sb,"+sql2
				+" where o.b_no = book.b_no "
				+ " and o.s_no = sb.s_no "
				+ " and o.b_no = sb.b_no "
				+ " order by o.amount desc";
		
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, "待付款");
			ps.setString(2, "%"+date+"%");
			ps.setString(3, s_no);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				saleAmount=new SaleAmount();
				String b_no=rs.getString("o.b_no");
				saleAmount.setB_no(b_no);
				saleAmount.setBookName(rs.getString("book.name"));
				saleAmount.setImgAddress(rs.getString("sb.img_address1"));
				saleAmount.setAmount(rs.getDouble("o.amount"));
				saleAmount.setPrifit(rs.getDouble("o.profit"));
				saleList.add(saleAmount);
			}
			
			return saleList;
		}catch(Exception e )
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
			
		}
		return saleList;
	}
	public List<SaleNumber> saleNumberSearch(String s_no,String date)
	{
		List<SaleNumber> saleList=new ArrayList<>();
		SaleNumber saleNumber=null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql2="(select order_form.b_no, order_form.s_no,sum(order_form.number*(order_form.price-purchase.price)) as profit, sum(order_form.number) as num from order_form, purchase"
				+ " where order_form.s_no = purchase.s_no "
				+ " and order_form.b_no = purchase.b_no "
				+ " and order_form.status <> ? "
				+ " and order_form.update_date like ? "
				+ " and order_form.s_no = ?"
				+ " group by order_form.b_no, order_form.s_no) as ord ";
		
		String sql="select ord.b_no,ord.num,ord.profit,book.name,sb.img_address1 from book,sb,"+sql2
				+" where ord.b_no = book.b_no "
				+ " and ord.s_no = sb.s_no "
				+ " and ord.b_no = sb.b_no"
				+ " order by ord.num desc";
		
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, "待付款");
			ps.setString(2, "%"+date+"%");
			ps.setString(3, s_no);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				saleNumber=new SaleNumber();
				saleNumber.setB_no(rs.getString("ord.b_no"));
				saleNumber.setBookName(rs.getString("book.name"));
				saleNumber.setImgAddress(rs.getString("sb.img_address1"));
				saleNumber.setNumber(rs.getInt("ord.num"));
				saleNumber.setPrifit(rs.getDouble("ord.profit"));
				saleList.add(saleNumber);
			}
			return saleList;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		return saleList;
		
	}
	
	
}
