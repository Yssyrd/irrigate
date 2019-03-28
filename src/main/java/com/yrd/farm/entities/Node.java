package com.yrd.farm.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_node")
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;							//序列
	
	@Column(length=50)
	private String name;						//结点名称
	
	@Column(length=50)
	private String SN;							//唯一识别码
	
	@Column(length=10)
	private String moduleAddr;					//模块地址
	
	@Column(length=10)
	private String channel;						//信道
	
	@Column
	private String site;						//位置
	
	@Column(length=2)
	private String nodeStatus;					//结点在线状态（0 在线，1 离线，2异常 ）
	
	@Column(length=2)
	private String openStatus;					//结点开启状态（0 打开，1关闭,3执行打开过程中，4执行关闭过程中 ）

	@Column
	private String proId;						//产品编号
	
	@Column
	private String version;						//版本号
	
	@Column
	private String proDate;						//出厂日期
	
	@Column
	private String terminal;					//关联的终端号
	
	@Column
	private String lastUpdate;					//上次更新时间
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}


	public String getModuleAddr() {
		return moduleAddr;
	}

	public void setModuleAddr(String moduleAddr) {
		this.moduleAddr = moduleAddr;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", SN=" + SN + ", moduleAddr=" + moduleAddr + ", channel="
				+ channel + ", site=" + site + ", nodeStatus=" + nodeStatus + ", openStatus=" + openStatus + ", proId="
				+ proId + ", version=" + version + ", proDate=" + proDate + ", terminal=" + terminal + ", lastUpdate="
				+ lastUpdate + ", loginDate=" + loginDate + ", delFlag=" + delFlag + "]";
	}


}
