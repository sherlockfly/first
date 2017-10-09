package com.drbwx.admin.common;

import java.util.List;

//没有？？？
public class MenuTreeNode1 {
	
	private int id;
	
	private String text;
	
	private List children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return text;
	}

	public void setName(String name) {
		this.text = name;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}  
}
