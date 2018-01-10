package com.autoseconds.exception;

public class AutoSecondsAppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8278625996886315607L;
	private String errorMessage;
	private String cause;

	public AutoSecondsAppException() {
		// TODO Auto-generated constructor stub
	}

	public AutoSecondsAppException(final String errorMessage, final String cause) {
		this.errorMessage = errorMessage;
		this.cause = cause;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}

}
