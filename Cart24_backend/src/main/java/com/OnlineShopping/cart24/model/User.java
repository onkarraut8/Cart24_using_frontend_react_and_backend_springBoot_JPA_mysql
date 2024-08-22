package com.OnlineShopping.cart24.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition ="varchar(3) default 'NO'")
	private String varify="NO";
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	/*
	 * @Email
	 * 
	 * @Size(min = 5, max = 30)
	 */
	@Column(length = 30, unique = true)
	private String emailId;
	
	@JsonIgnore
	private String password;
	@Column(unique=true)
	private String phoneNo;
	
	private String role;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getVarify() {
		return varify;
	}

	public void setVarify(String varify) {
		this.varify = varify;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", varify=" + varify + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", emailId=" + emailId + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", role=" + role + ", address=" + address + "]";
	}


	
}
