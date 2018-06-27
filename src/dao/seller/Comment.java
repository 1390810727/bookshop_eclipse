package dao.seller;

/**
 * 评论信息的封装类，在原有数据库基础上，多了c_name（顾客的姓名）属性，以供显示来用
 * @author KUIKUI
 */
public class Comment {
	private String c_id;
	private String c_name;
	private String comment_info;
	private String b_no;
	private String s_no;
	private String dateTime;
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getComment_info() {
		return comment_info;
	}
	public void setComment_info(String comment_info) {
		this.comment_info = comment_info;
	}
	public String getB_no() {
		return b_no;
	}
	public void setB_no(String b_no) {
		this.b_no = b_no;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
