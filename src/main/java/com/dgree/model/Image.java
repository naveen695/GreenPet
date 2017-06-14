package com.dgree.model;

public class Image {
private String name;
private String contentType;
private byte[] fileByte ;
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

}
