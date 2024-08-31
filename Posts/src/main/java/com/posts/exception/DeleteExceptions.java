package com.posts.exception;

public class DeleteExceptions extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errormsg;

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public DeleteExceptions(String errormsg) {
		super(errormsg);
		this.errormsg = errormsg;
	}

	public DeleteExceptions() {
		super();
	}

}
