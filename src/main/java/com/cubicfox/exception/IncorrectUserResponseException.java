package com.cubicfox.exception;

public class IncorrectUserResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectUserResponseException(final String message) {
		super(message);
	}
}
