package com.drbwx.admin.common;

/**
 * 
 *
 */
public enum RespCode {

	/**
	 * 0000 成功
	 */
	E_0000("0000000","成功"),
	
	/**
	 * 参数不合法
	 */
	E_0003("0003","输入参数不合法"),
	
	/**
	 * 业务系统错误
	 */
	E_9998("9998","业务系统错误"),
	
	/**
	 * 具体业务暂未实现
	 */
	E_9999("9999","具体业务暂未实现"),
	/**
	 * 数据库中没有此数据
	 */
	E_8998("8998","数据库中没有此数据"),
	/**
	 * 数据库操作失败
	 */
	E_8999("8999","数据库操作失败"),
	
    /**
     * 缓存系统异常
     */
    E_1005("1005", "系统异常");
	
    public String name;  
    
    public String code; 

    private RespCode(String code, String  name) {  
        this.name = name;  
        this.code = code;  
    }  
    
    @Override  
    public String toString() {  
        return this.code+"_"+this.name;  
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code; 
	}
    
}
