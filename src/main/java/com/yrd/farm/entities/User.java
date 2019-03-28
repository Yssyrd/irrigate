package com.yrd.farm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;								//序列ID
	
	@Column(length=50)
	private String nickName;						//姓名
	
	@Column(length=50)
	private String username;						//姓名
	
	@Column(length=1)
	private String gender;							//性别
	
	@Column
	private String password;						//密码
	
	@Column
	private String remark;							//备注
		
	@Column
	private String auth;							//权限
	
	@Column
	private String status;							//状态，1"有效"，0"无效"
	
	@Column
	private String phone;							//电话号码
	
	private String city;							//城市
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickName=" + nickName + ", username=" + username + ", gender=" + gender
				+ ", password=" + password + ", remark=" + remark + ", auth=" + auth + ", status=" + status + ", phone="
				+ phone + ", city=" + city + "]";
	}

}
