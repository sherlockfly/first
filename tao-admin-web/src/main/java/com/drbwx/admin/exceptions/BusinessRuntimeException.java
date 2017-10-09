package com.drbwx.admin.exceptions;

public class BusinessRuntimeException extends RuntimeException {
	private String errorCode;
	private String errorDesc;
	private static final long serialVersionUID = 1L;

	public BusinessRuntimeException() {
		super();
	}

	public BusinessRuntimeException(Throwable arg0) {
		super(arg0);
	}

	public BusinessRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * 业务异常错误码和错误描述构造器
	 * 
	 * @param errorCode
	 * @param errorDesc
	 * @author david.feng
	 * @creation 2014-7-4 上午9:27:53
	 */
	public BusinessRuntimeException(String errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	/**
	 * 业务异常信息、错误码和错误描述构造器
	 * 
	 * @param message
	 * @param errorCode
	 * @param errorDesc
	 * @author david.feng
	 * @creation 2014-7-4 上午10:24:53
	 */
	public BusinessRuntimeException(String message, String errorCode, String errorDesc) {
		super(message);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public BusinessRuntimeException(Throwable cause, String errorCode, String errorDesc) {
		super(cause);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public BusinessRuntimeException(String message, Throwable cause, String errorCode, String errorDesc) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
}
