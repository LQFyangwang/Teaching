package com.gs.common.bean;

public class ControllerResult {
	
	private int code;
	private String result;
	private String message;
	
	public ControllerResult() {
		
	}
	
	public ControllerResult(int code, String result, String message) {
		this.code = code;
		this.result = result;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ControllerResult getSuccessResult(String message) {
		return new ControllerResult(200, "success", message);
	}
	
	public static ControllerResult getFailResult(String message) {
		return new ControllerResult(500, "fail", message);
	}

}
