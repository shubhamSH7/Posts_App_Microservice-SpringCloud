package com.posts.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostsDTO {
	private int id;

	@NotNull
	@Size(min = 3, max = 256, message = "Post should be between 3 to 256 characters long")
	private String desc;

	private int userID;

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PostsDTO(String desc) {
		super();
		this.desc = desc;
	}

	public PostsDTO(int id, String desc, int userID) {
		super();
		this.id = id;
		this.desc = desc;
		this.userID= userID;
	}

	public PostsDTO() {
		super();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
