package old;
public class UserInfoItem {
	private String username;
	private String password;
	private String type;
	
	public UserInfoItem(String username, String password, int type) {
		this.username = username;
		this.password = password;
		if (type == 0) {
			this.type = "Buyer";
		} else {
			this.type = "Seller";
		}
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getType() {
		return type;
	}
}
