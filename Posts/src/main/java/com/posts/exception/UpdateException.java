package com.posts.exception;

public class UpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errormsg;

	public UpdateException(String errormsg) {
		super(errormsg);
		this.setErrormsg(errormsg);
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

}
