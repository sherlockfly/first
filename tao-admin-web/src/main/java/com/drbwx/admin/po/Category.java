package com.drbwx.admin.po;

import java.util.Date;

public class Category {
    private Integer id;

    private String name;

    private Byte grade;

    private Integer pid;

    private String remark;

    private Byte status;

    private Date creatTime;

    private Date updateTime;

    private String creator;

    private String picLink;

    private Integer sort;
    
    private String code; //编号
    
    private String pCode; //父编号

    public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink == null ? null : picLink.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", grade=" + grade
				+ ", pid=" + pid + ", remark=" + remark + ", status=" + status
				+ ", creatTime=" + creatTime + ", updateTime=" + updateTime
				+ ", creator=" + creator + ", picLink=" + picLink + ", sort="
				+ sort + ", code=" + code + ", pCode=" + pCode + "]";
	}
    
    
}