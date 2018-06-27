package model;

import java.util.List;

public class SameShopBook {
	private String s_no;
	private String shopName;
	private String createDate;
	private String c_id;
	private String customerName;
	private int sumNumber;
	private double sumPrice;
	private int a_no;
	
	private String status;
	
	private List<OrderFormInfo> orderFormInfoList;
	
	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	
	public int getSumNumber() {
		return sumNumber;
	}
	public void setSumNumber(int sumNumber) {
		this.sumNumber = sumNumber;
	}
	public double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public List<OrderFormInfo> getOrderFormInfoList() {
		return orderFormInfoList;
	}
	public void setOrderFormInfoList(List<OrderFormInfo> orderFormInfoList) {
		this.orderFormInfoList = orderFormInfoList;
	}
	
	
	
	
	
}
