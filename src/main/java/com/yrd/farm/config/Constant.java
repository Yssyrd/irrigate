package com.yrd.farm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:constant.properties")
@ConfigurationProperties(prefix = "constant")
public class Constant {
	
	private String NODE_ACK;
	private String NODE_OPEN_TAP;
	private String NODE_CLOSE_TAP;
	private String NODE_OPEN_TAP_OK;
	private String NODE_CLOSE_TAP_OK;
	private String NODE_OPEN_TAP_ERR;
	private String NODE_CLOSE_TAP_ERR;
	private String NODE_GET_TAP_STATUS;
	private String NODE_CONNECT;
	private String NODE_CONNECT_OK;
	private String NODE_ONLINE;
	private String NODE_OFFLINE;
	private String NODE_ALIVE;
	private String NODE_TIMEOUT;
	private String BIND_USER;
	private String BIND_USER_OK;
	private String BIND_SN_ERR;
	private String BIND_USERID_ERR;
	public String getNODE_ACK() {
		return NODE_ACK;
	}
	public void setNODE_ACK(String nODE_ACK) {
		NODE_ACK = nODE_ACK;
	}
	public String getNODE_OPEN_TAP() {
		return NODE_OPEN_TAP;
	}
	public void setNODE_OPEN_TAP(String nODE_OPEN_TAP) {
		NODE_OPEN_TAP = nODE_OPEN_TAP;
	}
	public String getNODE_CLOSE_TAP() {
		return NODE_CLOSE_TAP;
	}
	public void setNODE_CLOSE_TAP(String nODE_CLOSE_TAP) {
		NODE_CLOSE_TAP = nODE_CLOSE_TAP;
	}
	public String getNODE_OPEN_TAP_OK() {
		return NODE_OPEN_TAP_OK;
	}
	public void setNODE_OPEN_TAP_OK(String nODE_OPEN_TAP_OK) {
		NODE_OPEN_TAP_OK = nODE_OPEN_TAP_OK;
	}
	public String getNODE_CLOSE_TAP_OK() {
		return NODE_CLOSE_TAP_OK;
	}
	public void setNODE_CLOSE_TAP_OK(String nODE_CLOSE_TAP_OK) {
		NODE_CLOSE_TAP_OK = nODE_CLOSE_TAP_OK;
	}
	public String getNODE_OPEN_TAP_ERR() {
		return NODE_OPEN_TAP_ERR;
	}
	public void setNODE_OPEN_TAP_ERR(String nODE_OPEN_TAP_ERR) {
		NODE_OPEN_TAP_ERR = nODE_OPEN_TAP_ERR;
	}
	public String getNODE_CLOSE_TAP_ERR() {
		return NODE_CLOSE_TAP_ERR;
	}
	public void setNODE_CLOSE_TAP_ERR(String nODE_CLOSE_TAP_ERR) {
		NODE_CLOSE_TAP_ERR = nODE_CLOSE_TAP_ERR;
	}
	public String getNODE_GET_TAP_STATUS() {
		return NODE_GET_TAP_STATUS;
	}
	public void setNODE_GET_TAP_STATUS(String nODE_GET_TAP_STATUS) {
		NODE_GET_TAP_STATUS = nODE_GET_TAP_STATUS;
	}
	public String getNODE_CONNECT() {
		return NODE_CONNECT;
	}
	public void setNODE_CONNECT(String nODE_CONNECT) {
		NODE_CONNECT = nODE_CONNECT;
	}
	public String getNODE_CONNECT_OK() {
		return NODE_CONNECT_OK;
	}
	public void setNODE_CONNECT_OK(String nODE_CONNECT_OK) {
		NODE_CONNECT_OK = nODE_CONNECT_OK;
	}
	public String getNODE_ONLINE() {
		return NODE_ONLINE;
	}
	public void setNODE_ONLINE(String nODE_ONLINE) {
		NODE_ONLINE = nODE_ONLINE;
	}
	public String getNODE_OFFLINE() {
		return NODE_OFFLINE;
	}
	public void setNODE_OFFLINE(String nODE_OFFLINE) {
		NODE_OFFLINE = nODE_OFFLINE;
	}
	public String getNODE_ALIVE() {
		return NODE_ALIVE;
	}
	public void setNODE_ALIVE(String nODE_ALIVE) {
		NODE_ALIVE = nODE_ALIVE;
	}
	public String getNODE_TIMEOUT() {
		return NODE_TIMEOUT;
	}
	public void setNODE_TIMEOUT(String nODE_TIMEOUT) {
		NODE_TIMEOUT = nODE_TIMEOUT;
	}
	public String getBIND_USER() {
		return BIND_USER;
	}
	public void setBIND_USER(String bIND_USER) {
		BIND_USER = bIND_USER;
	}
	public String getBIND_USER_OK() {
		return BIND_USER_OK;
	}
	public void setBIND_USER_OK(String bIND_USER_OK) {
		BIND_USER_OK = bIND_USER_OK;
	}
	public String getBIND_SN_ERR() {
		return BIND_SN_ERR;
	}
	public void setBIND_SN_ERR(String bIND_SN_ERR) {
		BIND_SN_ERR = bIND_SN_ERR;
	}
	public String getBIND_USERID_ERR() {
		return BIND_USERID_ERR;
	}
	public void setBIND_USERID_ERR(String bIND_USERID_ERR) {
		BIND_USERID_ERR = bIND_USERID_ERR;
	}
}
