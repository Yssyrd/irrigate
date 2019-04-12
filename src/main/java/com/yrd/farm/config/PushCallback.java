package com.yrd.farm.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yrd.farm.component.WebSocketServer;
import com.yrd.farm.service.MqttService;

@Component
public class PushCallback implements MqttCallback {
	 
	@Autowired
	private MqttService mqttService;
	
    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }
 
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
 
    public static PushCallback callback;
    
    @PostConstruct
    public void init() {    
    	callback = this;
    } 
    
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
//        System.out.println("接收消息主题 : " + topic);
//        System.out.println("接收消息Qos : " + message.getQos());
//        System.out.println("接收消息内容 : " + new String(message.getPayload()));
        callback.mqttService.getCode(topic, new String(message.getPayload()));
        try {
			WebSocketServer.sendInfo("接收消息主题 : "+topic+"接收消息内容:"+new String(message.getPayload()),"");
		} catch (IOException e) {
			e.printStackTrace();
			
		}  
    }
}
