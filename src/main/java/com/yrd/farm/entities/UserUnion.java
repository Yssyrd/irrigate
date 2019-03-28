package com.yrd.farm.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_userunion")
public class UserUnion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;											//序列ID
	@Column
	private String openId;										//微信的openid
	@Column
	private String userId;										//关联的用户ID
	@Column
	private String type;										//登陆方式
	@Column
	private String unionId;										//适用于授权其他用户进行功能使用
	
	@Column
	private Timestamp loginDate;								//注册时间
	
	@Column
	private Timestamp updateDate;								//最后一次更新时间
	
	@Column
	private String avatar;										//会员头像
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Timestamp getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Override
	public String toString() {
		return "UserUnion [id=" + id + ", openId=" + openId + ", userId=" + userId + ", type=" + type + ", unionId="
				+ unionId + ", loginDate=" + loginDate + ", updateDate=" + updateDate + ", avatar=" + avatar + "]";
	}
	
}
