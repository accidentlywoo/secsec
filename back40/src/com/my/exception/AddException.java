package com.my.exception;

public class AddException extends Exception{
	private static final long serialVersionUID = 1L;

	public AddException() {
	}

	public AddException(String message) {
		super(message);
	}
}
