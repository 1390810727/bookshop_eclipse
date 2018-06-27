package model;

/**
 * 用于显示在购物车里的图书的信息
 * @author KUIKUI
 *
 */
public class ShopCardBook {
	private ShopCard shopCard;
	private String bookName;
	private String shopName;
	private double price;//单价
	private String imgAddress1;
	public String getImgAddress1() {
		return imgAddress1;
	}
	public void setImgAddress1(String imgAddress1) {
		this.imgAddress1 = imgAddress1;
	}
	public ShopCard getShopCard() {
		return shopCard;
	}
	public void setShopCard(ShopCard shopCard) {
		this.shopCard = shopCard;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
