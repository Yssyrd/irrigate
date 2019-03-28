package com.yrd.farm.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_record")
public class Record {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50)
	private String nodeId;															//结点ID
	
	@Column(length=1,name="exc_operate")
	private String excOperate;
	
	@Column(length = 50)
	private String flowStatistics;													//流量统计
	
	@Column
	private Timestamp beginTime;
	
	@Column
	private Timestamp endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getExcOperate() {
		return excOperate;
	}

	public void setExcOperate(String excOperate) {
		this.excOperate = excOperate;
	}

	public String getFlowStatistics() {
		return flowStatistics;
	}

	public void setFlowStatistics(String flowStatistics) {
		this.flowStatistics = flowStatistics;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", nodeId=" + nodeId + ", excOperate=" + excOperate + ", flowStatistics="
				+ flowStatistics + ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}
	
	
}
