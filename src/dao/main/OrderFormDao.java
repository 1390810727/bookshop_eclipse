package dao.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.Address;
import model.BookInfo;
import web.main.bean.Logistic;
import web.main.bean.OrderForm;
import web.main.bean.OrderFormInfo;
import web.main.bean.SameShopBook;

/**
 * 处理订单的dao层
 * @author KUIKUI
 *
 */
public class OrderFormDao {

	
	/**
	 * 根据传来的订单集合，将订单集合的信息存到数据库中。成为创建订单
	 * @param orderFormList
	 * @return
	 */
	public boolean createOrderForm(List<OrderForm> orderFormList)
	{
		OrderForm orderForm=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			con.setAutoCommit(false);
			for(OrderForm of: orderFormList) {
				ps=con.prepareStatement("insert into order_form(c_id,s_no,b_no,number,price,create_date,update_date,status,a_no)"
						+ " values(?,?,?,?,?,?,?,?,?)"
						+ "");
				ps.setString(1, of.getC_id());
				ps.setString(2, of.getS_no());
				ps.setString(3, of.getB_no());
				ps.setInt(4, of.getNumber());
				ps.setDouble(5, of.getPrice());
				ps.setString(6, of.getCreateDate());
				ps.setString(7, of.getUpdateDate());
				ps.setString(8, of.getStatus());
				ps.setInt(9, of.getA_no());
				int n=ps.executeUpdate();
				if(!(n>0)) {
					con.rollback();
					return false;
				}
			}
			con.commit();
			return true;
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		return false;
	}
	
	/**
	 * 因为临时需要这个需求，所以又添加了这个方法
	 * 根据店铺no获得 店铺的名称
	 */
	public String getShopNameByS_no(String s_no) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select name from shop where s_no = ?");
			ps.setString(1, s_no);
			rs=ps.executeQuery();
			if(rs.next()) {
				name=rs.getString(1);
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		return name;
	}
	
	/**
	 * 根据店铺号和图书编号获得图书的信息（包括图书的图片地址）
	 * @param s_no
	 * @param b_no
	 * @return 图书信息的封装类
	 */
	public BookInfo getBookInfo(String s_no,String b_no) {
		BookInfo bookInfo=new BookInfo();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select name,author,type,publish,img_address1 from book,sb "
					+ " where book.b_no=sb.b_no "
					+ " and book.b_no = ? "
					+ " and sb.s_no = ?");
			ps.setString(1, b_no);
			ps.setString(2,s_no);
			rs=ps.executeQuery();
			if(rs.next()) {
				
				bookInfo.setName(rs.getString("name"));
				bookInfo.setAuthor(rs.getString("author"));
				bookInfo.setType(rs.getString("type"));
				bookInfo.setPublish(rs.getString("publish"));
				bookInfo.setImgAddress1(rs.getString("img_address1"));
			}
			return bookInfo;
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		
		return null;
		
		
		
	}
	
	public List<Address> getAddressList(String c_id){
		List<Address> addressList=new ArrayList<Address>();
		Address address=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("select * from address "
									+ " where c_id = ?");
			ps.setString(1, c_id);	
			rs=ps.executeQuery();
			while(rs.next()) {
				address=new Address();
				address.setA_no(rs.getInt("a_no"));
				address.setC_id(c_id);
				address.setName(rs.getString("name"));
				address.setProvince(rs.getString("province"));
				address.setCity(rs.getString("city"));
				address.setCountry(rs.getString("country"));
				address.setStreet(rs.getString("street"));
				address.setPost_code(rs.getString("post_code"));
				address.setTell_no(rs.getString("tell_no"));
				
				addressList.add(address);
				
			}
			
			return addressList;
				
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		
		return null;
	}
	public List<SameShopBook> getOrderFormList(String c_id,String status){
		List<SameShopBook> sameShopBookList=new ArrayList<>();
		SameShopBook sameShopBook=null;
		List<OrderFormInfo> orderFormInfoList=null;
		OrderFormInfo orderFormInfo=null;
		OrderForm orderForm=null;
		List<String> createDateList=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			String sql="select create_date,c_id from order_form "
					+ " group by c_id,create_date,status"
					+ " having c_id = ?";
			String sql2="select * from order_form of,sb,book,shop "
					+ " where of.s_no=sb.s_no "
					+ " and of.b_no= sb.b_no"
					+ " and sb.b_no=book.b_no "
					+ " and sb.s_no = shop.s_no "
					+ " and of.c_id = ? "
					+ " and of.create_date = ?";
			if(status !=null) {
				
				sql+= " and status = ?";
			}
					
			ps=con.prepareStatement(sql);
			ps.setString(1, c_id);
			if(status !=null) {
				ps.setString(2, status);
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				createDateList.add(rs.getString("create_date"));
			}
			for(String createDate:createDateList) {
				ps=con.prepareStatement(sql2);
				ps.setString(1, c_id);
				ps.setString(2, createDate);
				
				
				rs=ps.executeQuery();
				
				
				orderFormInfoList=new ArrayList<>();
				String s_no=null;
				String shopName=null;
				int sumNum=0;
				double sumPrice=0;
				int a_no=0;
				while(rs.next()) {
					s_no=rs.getString("of.s_no");
					shopName=rs.getString("shop.name");
					sumNum+=rs.getInt("of.number");
					sumPrice+=(rs.getDouble("of.price"))*(rs.getInt("of.number"));
					a_no=rs.getInt("of.a_no");
					
					
					orderForm=new OrderForm();
					orderForm.setC_id(rs.getString("of.c_id"));
					orderForm.setB_no(rs.getString("of.b_no"));
					orderForm.setS_no(rs.getString("of.s_no"));
					orderForm.setNumber(rs.getInt("of.number"));
					orderForm.setCreateDate(rs.getString("create_date"));
					orderForm.setStatus(rs.getString("of.status"));
					orderForm.setUpdateDate(rs.getString("update_date"));
					orderForm.setPrice(rs.getDouble("of.price"));
					orderForm.setA_no(rs.getInt("of.a_no"));
					
					orderFormInfo=new OrderFormInfo();
					orderFormInfo.setOrderForm(orderForm);
					orderFormInfo.setBookName(rs.getString("book.name"));
					orderFormInfo.setAuthor(rs.getString("book.author"));
					orderFormInfo.setPublish(rs.getString("book.publish"));
					orderFormInfo.setType(rs.getString("book.type"));
					orderFormInfo.setImgAddress(rs.getString("img_address1"));
					
					orderFormInfoList.add(orderFormInfo);
				}
				sameShopBook=new SameShopBook();
				sameShopBook.setA_no(a_no);
				sameShopBook.setOrderFormInfoList(orderFormInfoList);
				sameShopBook.setS_no(s_no);
				sameShopBook.setShopName(shopName);
				sameShopBook.setStatus(status);
				sameShopBook.setSumNumber(sumNum);
				sameShopBook.setSumPrice(sumPrice);
				sameShopBookList.add(sameShopBook);
			}
				
				return sameShopBookList;
			}catch(Exception e) {
				
				e.printStackTrace();
			}finally {
				DBTools.close(con);
				DBTools.close(ps);
				DBTools.close(rs);
			}
		return null;
		
	}

	
	public boolean returnGoods(String createDate,String status,String s_no,String c_id) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			String sql="update order_form set status='待退货'"
					+ " where create_date=? "
					+ " and status = ? "
					+ " and s_no = ? "
					+ " and c_id = ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, createDate);
			ps.setString(2, status);
			ps.setString(3, s_no);
			ps.setString(4, c_id);
			int n=ps.executeUpdate();
			if(n>0) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		
		return false;
	}
	
	public boolean deleteOrderForm(String status, String c_id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBTools.getConnection();
			ps = con.prepareStatement("delete from order_form "
					+ " where status = ? "
					+ " and c_id = ?");
			ps.setString(1, status);
			ps.setString(2, c_id);
			int n = ps.executeUpdate();
			if(n>=0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		return false;
	}
	
	/**
	 * 订单中查看物流信息的dao层方法
	 * @param c_id  顾客id
	 * @param of_flag 订单物流标志，其实是指订单的创建时间
	 * @return 指定顾客id,订单标记，返回相应物流信息
	 */
	public List<Logistic> getLogistic(String c_id,String of_flag){
		List<Logistic> logisticList=new ArrayList<>();
		Logistic logistic=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			
			//要查询的物流要按照时间的降序排序
			ps=con.prepareStatement(
					"select * from logistic "
					+ " where c_id=? "
					+ " and of_flag = ?"
					+ " order by date desc");
			ps.setString(1, c_id);
			ps.setString(2, of_flag);
			rs=ps.executeQuery();
			while(rs.next()) {
				logistic=new Logistic();
				logistic.setAddress(rs.getString("address"));
				logistic.setDate(rs.getString("date"));
				logisticList.add(logistic);
			}
			return logisticList;
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
			DBTools.close(rs);
		}
		
		return logisticList;
		
	}
	
}
