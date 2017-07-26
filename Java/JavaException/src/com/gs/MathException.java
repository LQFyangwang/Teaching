package com.gs;

public class MathException extends Exception {

	public MathException() {
		super();
	}

	public MathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MathException(String message, Throwable cause) {
		super(message, cause);
	}

	public MathException(String message) {
		super(message);
	}

	public MathException(Throwable cause) {
		super(cause);
	}

}
