package br.com.demo.exception;

public class ErrorDetails {
	private String message;

	public ErrorDetails(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}