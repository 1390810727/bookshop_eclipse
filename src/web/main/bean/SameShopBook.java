package web.main.bean;

import java.util.List;

public class SameShopBook {
	private String s_no;
	private String shopName;
	private int sumNumber;
	private double sumPrice;
	private int a_no;
	
	private String status;
	
	
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
	private List<OrderFormInfo> orderFormInfoList;
	
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
