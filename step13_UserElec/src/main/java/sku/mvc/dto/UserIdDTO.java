package sku.mvc.dto;

public class UserIdDTO {
	
	private String userId;
	private String passWord;
	
	public UserIdDTO () {}
	public UserIdDTO(String userId, String passWord) {
		this.userId = userId;
		this.passWord = passWord;
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	
	
}
