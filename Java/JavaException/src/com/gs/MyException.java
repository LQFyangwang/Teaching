package com.gs;

public class MyException extends Exception {

	public MyException() {
		super();
		System.out.println("MyException");
	}
	
	public MyException(String message) {
		super(message);
		System.out.println("MyException");
	}
	
	public MyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MyException(Throwable cause) {
		super(cause);
	}

	public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
