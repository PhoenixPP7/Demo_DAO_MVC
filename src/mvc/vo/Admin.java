package mvc.vo;

import java.io.Serializable;

public class Admin implements Serializable {
	private String aid;
	private String password;
	
	public void setAid(String aid) {
		this.aid = aid;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAid() {
		return aid;
	}
	public String getPassword() {
		return password;
	}
}
