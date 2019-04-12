package com.yrd.farm.service;

import java.lang.reflect.Method;

public interface MqttService {

	public void getCode(String topic,String msg);
	public String analysisCode(String code,String dev, String user);
	public Method getGetMethod(Class objectClass, String fieldName);
	public Object invokeGet(Object o, String fieldName);
	public void sendSocket(String info,String user);
}
