package com.cubicfox.exception;

import lombok.Data;

@Data
public class ErrorMessage {

	private int statusCode;
	private String status;
	private String message;

	public ErrorMessage(final int statusCode, final String status) {
		this.statusCode = statusCode;
		this.status = status;
	}
	public ErrorMessage(final int statusCode, final String status, final String message) {
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
	}
}
