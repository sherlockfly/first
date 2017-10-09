package com.drbwx.admin.dto;

import java.util.Date;

public class ArticleCommentDto {
	
    private Long id;

    private Long uid;

    private Long aid;

    private String content;

    private Date creatDt;

    private Long reuid;

    private Long pid;

    private Byte isHuifu;

    private Byte status;

    private String clientIp;

    private Byte clientType;

    private Boolean asTop;
    
    private String nickname;
    
    private String headIocn;
    
    private String title;
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadIocn() {
		return headIocn;
	}

	public void setHeadIocn(String headIocn) {
		this.headIocn = headIocn;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatDt() {
        return creatDt;
    }

    public void setCreatDt(Date creatDt) {
        this.creatDt = creatDt;
    }

    public Long getReuid() {
        return reuid;
    }

    public void setReuid(Long reuid) {
        this.reuid = reuid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Byte getIsHuifu() {
        return isHuifu;
    }

    public void setIsHuifu(Byte isHuifu) {
        this.isHuifu = isHuifu;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public Byte getClientType() {
        return clientType;
    }

    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    public Boolean getAsTop() {
        return asTop;
    }

    public void setAsTop(Boolean asTop) {
        this.asTop = asTop;
    }
}
