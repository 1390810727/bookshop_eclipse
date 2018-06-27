package dao.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.BookInfo;
import model.SaleNumber;

/**
 * 获取本月销量高的前10名的图书
 * @author KUIKUI
 *
 */
public class SellWellDao {
	
	/**
	 * 从指定日期中获取销量前number项的图书列表
	 * @param date 指定时间
	 * @param number  获取的图书数
	 * @return
	 */
	public List<BookInfo> getSellWellBook(String date,int number)
	{
		List<BookInfo> bookInfoList=new ArrayList<>();
		BookInfo bookInfo=null;
		
		List<SaleNumber> saleList=new ArrayList<>();
		SaleNumber saleNumber=null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql2="(select order_form.b_no, order_form.s_no,sum(order_form.number*(order_form.price-purchase.price)) as profit, sum(order_form.number) as num from order_form, purchase"
				+ " where order_form.s_no = purchase.s_no "
				+ " and order_form.b_no = purchase.b_no "
				//+ " and order_form.status = ? "
				+ " and order_form.update_date like ? "
				+ " group by order_form.b_no, order_form.s_no) as ord ";
		
		String sql="select ord.b_no,ord.s_no, ord.num,ord.profit,book.name,sb.img_address1 from book,sb,"+sql2
				+" where ord.b_no = book.b_no "
				+ " and ord.s_no = sb.s_no "
				+ " and ord.b_no = sb.b_no"
				+ " order by ord.num desc"
				+ " limit 0,? ";
		
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			//ps.setString(1, "待评价");
			ps.setString(1, "%"+date+"%");
			ps.setInt(2, number);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				bookInfo=new BookInfo();
				bookInfo.setB_no(rs.getString("ord.b_no"));
				bookInfo.setName(rs.getString("book.name"));
				bookInfo.setNumber(rs.getInt("ord.num"));
				bookInfo.setS_no(rs.getString("ord.s_no"));
				bookInfoList.add(bookInfo);
			}
			return bookInfoList;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		return bookInfoList;
	}
}
