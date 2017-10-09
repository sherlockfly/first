package com.drbwx.admin.common;

import java.io.Serializable;
import java.util.Map;

public class CurrentOper implements Serializable{
	
	private static final long serialVersionUID = 3955017641437686400L;

	private String userid;
	
	private String loginName;
	
	private String realName;
	
	private String roles;
	
	private Map<String,String> functionMap;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Map<String, String> getFunctionMap() {
		return functionMap;
	}

	public void setFunctionMap(Map<String, String> functionMap) {
		this.functionMap = functionMap;
	}
}
