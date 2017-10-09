package com.drbwx.admin.vo;

import java.util.Date;

public class ExpertGoodsVo {
	private static final long serialVersionUID = -3150802252264379199L;

	private Long id;

    private Long euid;

    private String title;

    private String subhead;

    private Byte status;

    private Integer catId;

    private String tags;

    private Date creatDt;

    private Date updDt;

    private Byte recommend;

    private String url;

    private Double price;

    private String tinyUrl;

    private String picDefault;

    private String srcSite;

    private String srcUrl;

    private String discountUrl;

    private String activityUrl;

    private String content;
    
    private String titlePrice; //标题价格   审核人员附件  防止坐着没有明显的标记价格
    
    private String beizhu; //备注  审核人员备注  附件信息，文章底下显示
    
    private String fankui;//反馈     审核人员反馈

	public String getTitlePrice() {
		return titlePrice;
	}

	public void setTitlePrice(String titlePrice) {
		this.titlePrice = titlePrice;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getFankui() {
		return fankui;
	}

	public void setFankui(String fankui) {
		this.fankui = fankui;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEuid() {
		return euid;
	}

	public void setEuid(Long euid) {
		this.euid = euid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getCreatDt() {
		return creatDt;
	}

	public void setCreatDt(Date creatDt) {
		this.creatDt = creatDt;
	}

	public Date getUpdDt() {
		return updDt;
	}

	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}

	public Byte getRecommend() {
		return recommend;
	}

	public void setRecommend(Byte recommend) {
		this.recommend = recommend;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTinyUrl() {
		return tinyUrl;
	}

	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}

	public String getPicDefault() {
		return picDefault;
	}

	public void setPicDefault(String picDefault) {
		this.picDefault = picDefault;
	}

	public String getSrcSite() {
		return srcSite;
	}

	public void setSrcSite(String srcSite) {
		this.srcSite = srcSite;
	}

	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}

	public String getDiscountUrl() {
		return discountUrl;
	}

	public void setDiscountUrl(String discountUrl) {
		this.discountUrl = discountUrl;
	}

	public String getActivityUrl() {
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
