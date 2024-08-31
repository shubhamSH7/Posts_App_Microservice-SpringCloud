package com.accounts.user.entity;

public class UsersDTO {
	private String username;
	private int id;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsersDTO(String username, int id) {
		super();
		this.username = username;
		this.id = id;
	}

	public UsersDTO() {
		super();
	}
	
	
}
