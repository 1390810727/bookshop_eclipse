package model;
/**
 * 登陆系统管理员的账户封装类
 * @author KUIKUI
 *
 */
public class User {
	private String id;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
