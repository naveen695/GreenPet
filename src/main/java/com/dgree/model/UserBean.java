package com.dgree.model;

public class UserBean {

	private String userFirstName;
	private String userLastName;
	private String email;
	private String hash;
	private String password;
	private String conformpPssword;
	private String mobilenumber;
	private long userid;

	private String subject;
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "UserBean [userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", email=" + email
				+ ", password=" + password + ", conformpPssword=" + conformpPssword + "]";
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConformpPssword() {
		return conformpPssword;
	}
	public void setConformpPssword(String conformpPssword) {
		this.conformpPssword = conformpPssword;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}


}
