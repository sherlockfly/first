package com.drbwx.admin.dto;

import java.io.Serializable;
import java.util.Date;

public class ExpertApplyActionDto implements Serializable{

	private static final long serialVersionUID = 3556556805377159465L;
	
    private Long id;

    private Long applyId;

    private Long uid;

    private Long verifyId;

    private String verifyName;

    private Short verifyStatus;

    private Date actionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(Long verifyId) {
		this.verifyId = verifyId;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public Short getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Short verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
}
