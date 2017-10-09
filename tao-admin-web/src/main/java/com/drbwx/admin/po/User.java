package com.drbwx.admin.po;

import java.util.Date;

public class User {
	private Long id;

    private String loginName;

    private String pwd;

    private String nickname;

    private String email;

    private String phone;

    private String city;

    private Byte gender;

    private String birthday;

    private Date lastLoginDate;

    private String lastLoginIp;

    private Date updateDate;

    private String regIp;

    private Byte grade;

    private Byte status;

    private String headIocn;

    private String realName;
    
    private Integer score = 0; //积分
    
    private Integer goodsCount = 0;
    
    private Integer articleCount = 0;
    
    private Integer askCount = 0;
    
    private Integer answerCount = 0;
    
    private Date lastFabuDt;
    
    private String intro;
    
    private Integer fansNum = 0;
    
    private Integer attentionNum = 0;
    
    private Date signinDt;
    
    private Integer goldCoin = 0;
    
    private Integer experience = 0;
    
    private Integer signDays ; //连续签到
    
    private String remark;
    
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSignDays() {
		return signDays;
	}

	public void setSignDays(Integer signDays) {
		this.signDays = signDays;
	}

	public Integer getGoldCoin() {
		return goldCoin;
	}

	public void setGoldCoin(Integer goldCoin) {
		this.goldCoin = goldCoin;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Date getSigninDt() {
		return signinDt;
	}

	public void setSigninDt(Date signinDt) {
		this.signinDt = signinDt;
	}

	public Integer getFansNum() {
		return fansNum;
	}

	public void setFansNum(Integer fansNum) {
		if(fansNum < 0){
			fansNum = 0;
		}
		this.fansNum = fansNum;
	}

	public Integer getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(Integer attentionNum) {
		if(attentionNum < 0){
			attentionNum = 0;
		}
		this.attentionNum = attentionNum;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getLastFabuDt() {
		return lastFabuDt;
	}

	public void setLastFabuDt(Date lastFabuDt) {
		this.lastFabuDt = lastFabuDt;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Integer getAskCount() {
		return askCount;
	}

	public void setAskCount(Integer askCount) {
		this.askCount = askCount;
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	public Byte getGrade() {
		return grade;
	}

	public void setGrade(Byte grade) {
		this.grade = grade;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getHeadIocn() {
		return headIocn;
	}

	public void setHeadIocn(String headIocn) {
		this.headIocn = headIocn;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
    
}