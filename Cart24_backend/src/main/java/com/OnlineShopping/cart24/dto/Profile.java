package com.OnlineShopping.cart24.dto;

public class Profile {
	
	private int id;
	private String varify;
	
	private String firstName;

	private String lastName;
	
	private String gender;

	private String emailId;

	private String password;

	private String phoneNo;

	private String street;

	private String city;
	
    private String state;
	
	private String country;

	private int pincode;
	
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public String getVarify() {
		return varify;
	}

	public void setVarify(String varify) {
		this.varify = varify;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", varify=" + varify + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", emailId=" + emailId + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", street=" + street + ", city=" + city + ", state=" + state + ", country=" + country + ", pincode="
				+ pincode + ", role=" + role + "]";
	}

	
	
	

}