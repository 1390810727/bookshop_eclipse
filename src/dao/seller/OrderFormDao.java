package dao.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBTools;
import model.OrderForm;
import model.SameShopBook;
import model.OrderFormInfo;

/**
 * 封装一些常用的订单管理操作的方法
 * @author KUIKUI
 *
 */
public class OrderFormDao {

	/**public List<OrderFormInfo> orderFormInfoList(String s_no,String status)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<OrderFormInfo> orderFormInfoList=new ArrayList<>();
		
		OrderFormInfo orderFormInfo=null;
		
		Logistics logistics=new Logistics();
		List<Logistics> logList=null;
		
		//获得指定店铺，指定订单状态的订单
		List<OrderForm> orderFormList=getOrderForm(s_no, status);
		
		//然后对它进行循环遍历，查询每一个订单的物流信息
		for(OrderForm orderForm:orderFormList)
		{
			logList=new ArrayList<>();
			orderFormInfo=new OrderFormInfo();
			
			//获取订单号
			int o_no=orderForm.getO_no();
			
			try {
				con=DBTools.getConnection();
				ps=con.prepareStatement("select * from logistics where o_no=? order by date");
				ps.setInt(1, o_no);
				rs=ps.executeQuery();
				
				while(rs.next())
				{
					logistics=new Logistics();
					logistics.setO_no(o_no);
					logistics.setAddress(rs.getString("address"));
					logistics.setDate(rs.getString("date"));
					logList.add(logistics);
				}
				orderFormInfo.setOrderForm(orderForm);
				orderFormInfo.setLogList(logList);
				
				orderFormInfoList.add(orderFormInfo);
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally {
				DBTools.close(con);
				DBTools.close(ps);
				DBTools.close(rs);
			}
		}
		return orderFormInfoList;
	}
	*/
	
	
	/**
	 * 获取指定状态的订单
	 * @param s_no
	 * @param status
	 * @return
	 */
	/**public List<OrderForm> getOrderForm(String s_no,String status)
	{
		List<OrderForm> orderFormList=new ArrayList<>();
		OrderForm orderForm=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select * from order_form,book,customer,shop,sb where"
				+ " order_form.b_no = sb.b_no"
				+ " and order_form.c_id = customer.c_id"
				+ " and order_form.s_no = sb.s_no"
				+ " and sb.s_no = shop.s_no"
				+ " and sb.b_no = book.b_no"
				+ " and order_form.s_no = ? "
				+ " and status = ?";
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, s_no);
			ps.setString(2, status);
			
			rs=ps.executeQuery();
			while(rs.next())
			{
				orderForm=new OrderForm();
				orderForm.setO_no(rs.getInt("o_no"));
				orderForm.setS_no(rs.getString("s_no"));
				orderForm.setB_no(rs.getString("b_no"));
				orderForm.setC_id(rs.getString("c_id"));
				orderForm.setBookName(rs.getString("book.name"));
				orderForm.setCustomerName(rs.getString("customer.name"));
				orderForm.setShopName(rs.getString("shop.name"));
				orderForm.setNumber(rs.getInt("number"));
				orderForm.setPrice(rs.getDouble("order_form.price"));
				orderForm.setCreate_date(rs.getString("create_date"));
				orderForm.setStatus(rs.getString("status"));
				orderForm.setUpdate_date(rs.getString("update_date"));
				orderForm.setImgAddress(rs.getString("sb.img_address1"));
				orderFormList.add(orderForm);
			}
			return orderFormList;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		return orderFormList;
	}
	
	public boolean updateLog(String o_no,String newAddress)

	{
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("insert into logistics values(?,?,now());");
			ps.setInt(1, Integer.parseInt(o_no.trim()));
			ps.setString(2, newAddress);
			int n=ps.executeUpdate();
			if(n>0)
			{
				return true;
			}else {
				return false;
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		
		return false;
	}
	
	public boolean updatePrice(String o_no, double price)
	{
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("update  order_form set price = ?  where o_no = ?");
			ps.setDouble(1, price);
			ps.setString(2, o_no);
			int n=ps.executeUpdate();
			return true;
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBTools.close(con);
			DBTools.close(ps);
		}
		
		return false;
	}	
	*/



	public List<SameShopBook> getOrderFormList(String s_no,String status){
		List<SameShopBook> sameShopBookList=new ArrayList<>();
		SameShopBook sameShopBook=null;
		List<OrderFormInfo> orderFormInfoList=null;
		OrderFormInfo orderFormInfo=null;
		OrderForm orderForm=null;
		List<String> createDateList=new ArrayList<>();
		List<String> c_idList=new ArrayList<>();
		System.out.println(s_no+"   "+status);
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBTools.getConnection();
			String sql="select create_date,c_id from order_form "
					+ " where s_no = ?"
					+ " group by c_id,create_date,status "
					+ " having status = ?";
			String sql2="select * from order_form of,sb,book,customer "
					+ " where of.s_no=sb.s_no "
					+ " and of.b_no= sb.b_no"
					+ " and sb.b_no=book.b_no "
					+ " and of.c_id = customer.c_id"
					+ " and of.s_no = ? "
					+ " and of.c_id = ? "
					+ " and of.create_date = ?";
				
			ps=con.prepareStatement(sql);
			ps.setString(1, s_no);
			ps.setString(2, status);
			rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println("test");
				c_idList.add(rs.getString("c_id"));
				createDateList.add(rs.getString("create_date"));
			}
			//System.out.println("第一个sql查询结果的长度："+rs.getRow());
			for(int i=0;i<c_idList.size();i++) {
				ps=con.prepareStatement(sql2);
				ps.setString(1, s_no);
				ps.setString(2, c_idList.get(i));
				ps.setString(3, createDateList.get(i));
				
				
				rs=ps.executeQuery();
				
				
				orderFormInfoList=new ArrayList<>();
				String c_id=c_idList.get(i);
				String customerName=null;
				int sumNum=0;
				double sumPrice=0;
				int a_no=0;
				while(rs.next()) {
					
					customerName=rs.getString("customer.name");
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
				sameShopBook.setC_id(c_id);
				sameShopBook.setCreateDate(createDateList.get(i));
				sameShopBook.setCustomerName(customerName);
				
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

	/**
	 * 商家发货，指定订单创建日期和顾客id，将指定状态的订单改为  给出的状态
	 * @param createDate
	 * @param c_id
	 * @return
	 */
	public boolean deliverBook(String createDate,String c_id ,String preStatus, String nextStatus) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBTools.getConnection();
			ps=con.prepareStatement("update order_form set status = ? "
					+ " where c_id = ? "
					+ " and create_date = ?"
					+ " and status = ? ");
			ps.setString(1, nextStatus);
			ps.setString(2, c_id);
			ps.setString(3, createDate);
			ps.setString(4, preStatus);
			
			int n = ps.executeUpdate();
			if(n>0) {
				 return true;
			} else {
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
