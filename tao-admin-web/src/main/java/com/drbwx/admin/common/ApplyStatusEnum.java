package com.drbwx.admin.common;

/**
 * 
 * <p>Description: 申请状态<／p>
 * <p>Company: drbwx<／p>
 * @author zhaolin
 * @date 2016年11月10日
 * @version 1.0
 */
public enum ApplyStatusEnum {

	shenhezhong((short)1), tongguo((short)2), weigongguo((short)3);

	// 成员变量
	private short status;

	// 构造方法
	private ApplyStatusEnum(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public static void main(String[] args) {
		System.out.println(ApplyStatusEnum.shenhezhong.getStatus());
	}
}
