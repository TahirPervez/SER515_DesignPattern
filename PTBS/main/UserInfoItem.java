package main;
public class UserInfoItem {
	private String username;
	private String password;
	private int type;
	
	public UserInfoItem(String username, String password, int type) {
		this.username = username;
		this.password = password;
		this.type = type; // 0 = buyer, 1 = seller
	}
	public UserInfoItem(String username, int type) {
		this.username = username;
		this.type = type; // 0 = buyer, 1 = seller
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getType() {
		return type;
	}
}
