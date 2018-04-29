package com.superInvent.POJO;

import java.sql.Timestamp;

public class Users {
	private String name;
	private String email;
	private String password;
	private String mobile;
	private String address;
	private Boolean is_admin;
	private Timestamp last_login;
	private Timestamp createdAt;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getLast_login() {
		return last_login;
	}
	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(Boolean is_admin) {
		this.is_admin = is_admin;
	}
	@Override
	public String toString() {
		return "Users [name=" + name + ", email=" + email + ", mobile=" + mobile + ", address=" + address
				+ ", is_admin=" + is_admin + ", last_login=" + last_login + ", createdAt=" + createdAt + "]";
	}
	
}