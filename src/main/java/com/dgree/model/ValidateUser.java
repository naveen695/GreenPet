package com.dgree.model;

public class ValidateUser {
@Override
	public String toString() {
		return "ValidateUser [userid=" + userid + ", isNewUser=" + isNewUser + ", LoginStauts=" + LoginStauts
				+ ", isLoginValid=" + isLoginValid + "]";
	}
private long userid;
private boolean isNewUser;
private String LoginStauts;
private boolean isLoginValid;
public long getUserid() {
	return userid;
}
public void setUserid(long userid) {
	this.userid = userid;
}
public boolean isNewUser() {
	return isNewUser;
}
public void setNewUser(boolean isNewUser) {
	this.isNewUser = isNewUser;
}
public boolean isLoginValid() {
	return isLoginValid;
}
public void setLoginValid(boolean isLoginValid) {
	this.isLoginValid = isLoginValid;
}
public String getLoginStauts() {
	return LoginStauts;
}
public void setLoginStauts(String loginStauts) {
	LoginStauts = loginStauts;
}
}
