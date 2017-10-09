package com.drbwx.admin.common;

public class AjaxResult {
	
	private int status = 200;
	private String msg = "操作成功";
	private String body;
	
	public AjaxResult(int status,String msg){
		this.status = status;
		this.msg = msg;
	}
	
	public AjaxResult(){
		
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
