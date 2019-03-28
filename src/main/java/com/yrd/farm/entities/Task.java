package com.yrd.farm.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;								//序列
	
	@Column(name="execute_time")
	private Timestamp executeTime;					//执行时间点
	
	@Column(length=2,name="opr_repeat")
	private String oprRepeat;						/*
														重复操作
														0（周日）1（周一），2（周二），3（周三），
														4（周四），5（周五），6（周六）
														比如024代表周日，周二，周四执行
	 												*/
							
	@Column(length=1,name="exc_operate")
	private String excOperate;							//操作（0 打开， 1 关闭）
	
	@Column
	private String userId;							//用户ID
	
	@Column
	private String nodeId;							//结点ID
	
	@Column
	private String remark;							//备注
	
	@Column
	private Timestamp loginDate;					//注册时间
	
	@Column
	private Timestamp logoutDate;					//注销时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Timestamp executeTime) {
		this.executeTime = executeTime;
	}

	public String getRepeat() {
		return oprRepeat;
	}

	public void setRepeat(String repeat) {
		this.oprRepeat = repeat;
	}

	public String getOperate() {
		return excOperate;
	}

	public void setOperate(String operate) {
		this.excOperate = operate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public Timestamp getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Timestamp logoutDate) {
		this.logoutDate = logoutDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", executeTime=" + executeTime + ", repeat=" + oprRepeat + ", operate=" + excOperate
				+ ", userId=" + userId + ", nodeId=" + nodeId + ", remark=" + remark + ", loginDate=" + loginDate
				+ ", logoutDate=" + logoutDate + "]";
	}
	
	
	
}
