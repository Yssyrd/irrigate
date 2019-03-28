package com.yrd.farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yrd.farm.config.MqttPushClient;

@Controller
public class MqttController {
    
	@Autowired
    private MqttPushClient mqttPushClient;
    
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
    @RequestMapping("/mqttTest")
    @ResponseBody
    public String mqttTest(){
        String kdTopic = "/dev/15345715326";
        mqttPushClient.publish(0, false, kdTopic, "yssyrd");
        return "123";
    }
}
