package com.posts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Posts_Micro")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	@Column(name = "Descriptions")
	private String desc;

	private int userID;

	public Post() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Post(String desc, int userID) {
		super();
		this.desc = desc;
		this.userID = userID;
	}

}
