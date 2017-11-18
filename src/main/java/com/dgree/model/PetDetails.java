package com.dgree.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PetDetails {
private String petname;

private String id;
private String address1;
private String address2;
private String state;
private String city;
private String county;
private String country;
private String zip;
private Image image;
private List<Image> imageList;
private UserBean userBean;
private LoginUserDetails loginUserDetails;
private Map<Object,Image> imageMap;
private String contentType;
private String insertedDate;
private Date date;
private String petDesc;
private Number totalLikes;
public Image getImage() {
	return image;
}
public void setImage(Image image) {
	this.image = image;
}
public List<Image> getImageList() {
	return imageList;
}
public void setImageList(List<Image> imageList) {
	this.imageList = imageList;
}
public Map<Object, Image> getImageMap() {
	return imageMap;
}
public void setImageMap(Map<Object, Image> imageMap) {
	this.imageMap = imageMap;
}
private String latitude;
private String longittude;
private Integer longittudeInt;
@Override
public String toString() {
	return "PetDetails [petname=" + petname + ", address1=" + address1 + ", address2=" + address2 + ", state=" + state
			+ ", city=" + city + ", county=" + county + ", country=" + country + ", zip=" + zip + ", latitude="
			+ latitude + ", longiute="  + ", loongitudeInt="  + ", lattitudeInt="
			+ lattitudeInt + "]";
}
public String getPetname() {
	return petname;
}
public void setPetname(String petname) {
	this.petname = petname;
}
public String getAddress1() {
	return address1;
}
public void setAddress1(String address1) {
	this.address1 = address1;
}
public String getAddress2() {
	return address2;
}
public void setAddress2(String address2) {
	this.address2 = address2;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getZip() {
	return zip;
}
public void setZip(String zip) {
	this.zip = zip;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}

public Integer getLattitudeInt() {
	return lattitudeInt;
}
public void setLattitudeInt(Integer lattitudeInt) {
	this.lattitudeInt = lattitudeInt;
}
public String getCounty() {
	return county;
}
public void setCounty(String county) {
	this.county = county;
}
public Integer getLongittudeInt() {
	return longittudeInt;
}
public void setLongittudeInt(Integer longittudeInt) {
	this.longittudeInt = longittudeInt;
}
public String getLongittude() {
	return longittude;
}
public void setLongittude(String longittude) {
	this.longittude = longittude;
}
public UserBean getUserBean() {
	return userBean;
}
public void setUserBean(UserBean userBean) {
	this.userBean = userBean;
}
public LoginUserDetails getLoginUserDetails() {
	return loginUserDetails;
}
public void setLoginUserDetails(LoginUserDetails loginUserDetails) {
	this.loginUserDetails = loginUserDetails;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getInsertedDate() {
	return insertedDate;
}
public void setInsertedDate(String insertedDate) {
	this.insertedDate = insertedDate;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getContentType() {
	return contentType;
}
public void setContentType(String contentType) {
	this.contentType = contentType;
}
public String getPetDesc() {
	return petDesc;
}
public void setPetDesc(String petDesc) {
	this.petDesc = petDesc;
}
public Number getTotalLikes() {
	return totalLikes;
}
public void setTotalLikes(Number totalLikes) {
	this.totalLikes = totalLikes;
}
private Integer lattitudeInt;

}
