package model;


/**
 * 封装指定店铺的图书
 * @author KUIKUI
 *
 */
public class BookInfo {
	private String b_no;
	private String name;
	private String type;
	private String author;
	private double price;
	private double sell_price;
	private int number;
	private String publish;
	private String publish_date;
	private String book_info;
	private String imgAddress1;
	private String imgAddress2;
	private String imgAddress3;
	private String isSell;
	//所属店铺
	private String s_no;
	public String getB_no() {
		return b_no;
	}
	public void setB_no(String b_no) {
		this.b_no = b_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSell_price() {
		return sell_price;
	}
	public void setSell_price(double sell_price) {
		this.sell_price = sell_price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getBook_info() {
		return book_info;
	}
	public void setBook_info(String book_info) {
		this.book_info = book_info;
	}
	public String getImgAddress1() {
		return imgAddress1;
	}
	public void setImgAddress1(String imgAddress1) {
		this.imgAddress1 = imgAddress1;
	}
	public String getImgAddress2() {
		return imgAddress2;
	}
	public void setImgAddress2(String imgAddress2) {
		this.imgAddress2 = imgAddress2;
	}
	public String getImgAddress3() {
		return imgAddress3;
	}
	public void setImgAddress3(String imgAddress3) {
		this.imgAddress3 = imgAddress3;
	}
	public String getIsSell() {
		return isSell;
	}
	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
}
