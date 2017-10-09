package com.drbwx.admin.common;

public enum CommonStatusEnum {
	
	keyong((byte)1), jinyong((byte)2);

	// 成员变量
	private byte status;

	// 构造方法
	private CommonStatusEnum(byte status) {
		this.status = status;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}
