package com.drbwx.admin.dto;

import java.io.Serializable;
import java.util.Date;

public class ExpertGoodsDto implements Serializable{
	private static final long serialVersionUID = -3150802252264379199L;

	private Long id;

    private Long uid;

    private String title;

    private String subhead; //一句话总结推荐理由

    private String content; //商品详情
    
    private String brand; //品牌

    private Short status;

    private Integer catId;
    
    private Integer catId1;
    
	private String tags; //标签

    private String creatDt;

    private Date updDt;

    private Byte recommend;

    private String url;

    private Double price;

    private String tinyUrl;

    private String picDef;

    private String srcSite;

    private String discountUrl;

    private String activityUrl;
    
    private Integer collectCount = 0;
    private Integer commentCount = 0;
    private Integer upCount = 0;
    private Integer downCount = 0;
    private Integer readCount = 0;
    
    private String nickname;
    
    private String headIocn;
    
    private Short type;
    
    private String fabiao_date;
    
    private String check_date;
    
    private String name;
    
    private String names;
    
    public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public Integer getCatId1() {
		return catId1;
	}

	public void setCatId1(Integer catId1) {
		this.catId1 = catId1;
	}
    
    public String getFabiao_date() {
		return fabiao_date;
	}

	public void setFabiao_date(String fabiao_date) {
		this.fabiao_date = fabiao_date;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
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

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
    
	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getUpCount() {
		return upCount;
	}

	public void setUpCount(Integer upCount) {
		this.upCount = upCount;
	}

	public Integer getDownCount() {
		return downCount;
	}

	public void setDownCount(Integer downCount) {
		this.downCount = downCount;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
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

	public String getCreatDt() {
		return creatDt;
	}

	public void setCreatDt(String creatDt) {
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

	public String getPicDef() {
		return picDef;
	}

	public void setPicDef(String picDef) {
		this.picDef = picDef;
	}

	public String getSrcSite() {
		return srcSite;
	}

	public void setSrcSite(String srcSite) {
		this.srcSite = srcSite;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
    
}
