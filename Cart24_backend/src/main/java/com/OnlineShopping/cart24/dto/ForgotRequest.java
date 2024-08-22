package com.OnlineShopping.cart24.dto;

public class ForgotRequest {
	
	private int id;
	private String emailId;
	private String password;
	private String role;
	private String phoneNo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "UserLoginRequest [emailId=" + emailId + ", password=" + password + ", role=" + role + ", phoneNo="
				+ phoneNo + "]";
	}
	
	
	
}
