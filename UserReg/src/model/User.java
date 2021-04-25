package model;

public class User {

	private String userid;
	private String username;
	private String name;
	private String password;

	
	
	public User() {
		
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String UserId, String UserName, String Name, String UPassword ) {
		super();
		this.userid = UserId;
		this.username = UserName;
		this.name = Name;
		this.password = UPassword;

	}
}
