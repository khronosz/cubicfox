package com.cubicfox.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(final String message) {
		super(message);
	}
}
