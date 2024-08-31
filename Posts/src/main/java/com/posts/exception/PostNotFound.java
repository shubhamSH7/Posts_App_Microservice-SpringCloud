package com.posts.exception;

public class PostNotFound extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errormsg;

	public PostNotFound() {
		super();
	}

	public PostNotFound(String msg) {
		super(msg);
		this.errormsg = msg;
	}

	public String getMsg() {
		return errormsg;
	}

	public void setMsg(String msg) {
		this.errormsg = msg;
	}

}
