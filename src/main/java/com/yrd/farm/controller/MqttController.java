package com.yrd.farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yrd.farm.config.Constant;
import com.yrd.farm.config.MqttPushClient;
import com.yrd.farm.service.MqttService;

import net.sf.json.JSONObject;

@Controller
public class MqttController {
    
	@Autowired
    private MqttPushClient mqttPushClient;
	
	@Autowired
	private MqttService mqttService;
    
	@Autowired
	private Constant constant;
	
    @RequestMapping("/hello")
    @ResponseBody
    public String sendHello(){
        String kdTopic = "topic1";
        mqttPushClient.publish(0, false, kdTopic, "15345715326");
        mqttPushClient.subscribe(kdTopic);
        mqttPushClient.subscribe("$SYS/brokers/+/clients/+/connected");
        mqttPushClient.subscribe("$SYS/brokers/+/clients/+/disconnected");
        return "123";
    }
    
    @RequestMapping("/sendMqtt")
    @ResponseBody
    public String sendMqtt(String topic,String code){
    	Object invokeGet = mqttService.invokeGet(constant, code);
        JSONObject obj = new JSONObject();
        obj.put("code", invokeGet);
        obj.put("msg", "");
        mqttPushClient.publish(0, false, topic, obj.toString());
        return "123";
    }
}
