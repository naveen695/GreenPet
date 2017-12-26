package com.dgree.model;

import java.util.List;

public class PetComments {
	private String petID;
	private List<Comments> comments;

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public String getPetID() {
		return petID;
	}

	public void setPetID(String petID) {
		this.petID = petID;
	}

}
