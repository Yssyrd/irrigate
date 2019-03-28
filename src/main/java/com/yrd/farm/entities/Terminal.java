package com.yrd.farm.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_teiminal")
public class Terminal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50)
	private String physicalAddr;								//物理地址
	
	@Column(length=10)
	private String loraAddr;									//Lora地址
	
	@Column(length=10)
	private String loraChannel;									//Lora信道
	
	@Column
	private String userId;										//关联的用户
	
	@Column
	private String SN;											//16位唯一识别码
	
	@Column
	private String proId;										//出厂编号
	
	@Column
	private String version;										//版本号
	
	@Column
	private String proDate;										//出厂日期
	
	@Column
	private String name;										//终端名称
	
	private String status;										//状态信息
	
	@Column(length=1000)
	private String nodeIds;
	
	@Column
	private Timestamp loginDate;
	
	@Column
	private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhysicalAddr() {
		return physicalAddr;
	}

	public void setPhysicalAddr(String physicalAddr) {
		this.physicalAddr = physicalAddr;
	}

	public String getLoraAddr() {
		return loraAddr;
	}

	public void setLoraAddr(String loraAddr) {
		this.loraAddr = loraAddr;
	}

	public String getLoraChannel() {
		return loraChannel;
	}

	public void setLoraChannel(String loraChannel) {
		this.loraChannel = loraChannel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(String nodeIds) {
		this.nodeIds = nodeIds;
	}

	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProDate() {
		return proDate;
	}

	public void setProDate(String proDate) {
		this.proDate = proDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	
}
