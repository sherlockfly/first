package com.drbwx.admin.common;

import java.io.Serializable;
import java.util.List;

public class PageResultDto<T> implements Serializable{

	private static final long serialVersionUID = 1080132588727998458L;
	
	private int total;
	
	private List<T> rows;
	
	public PageResultDto (int total,List<T> rows){
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
