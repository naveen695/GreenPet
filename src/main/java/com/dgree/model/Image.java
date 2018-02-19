package com.dgree.model;

public class Image {
private String name;
private String ids;
private boolean liked;
private String contentType;
private byte[] fileByte ;
private boolean validate ;
private Integer likes;
private Integer Dislikes;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContentType() {
	return contentType;
}
public void setContentType(String contentType) {
	this.contentType = contentType;
}
public byte[] getFileByte() {
	return fileByte;
}
public void setFileByte(byte[] fileByte) {
	this.fileByte = fileByte;
}
public Integer getDislikes() {
	return Dislikes;
}
public void setDislikes(Integer dislikes) {
	Dislikes = dislikes;
}
public Integer getLikes() {
	return likes;
}
public void setLikes(Integer likes) {
	this.likes = likes;
}
public String getIds() {
	return ids;
}
public void setIds(String ids) {
	this.ids = ids;
}
public boolean isLiked() {
	return liked;
}
public void setLiked(boolean liked) {
	this.liked = liked;
}
public boolean isValidate() {
	return validate;
}
public void setValidate(boolean validate) {
	this.validate = validate;
}

}
