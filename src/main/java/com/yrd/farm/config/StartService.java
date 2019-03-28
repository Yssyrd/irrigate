package com.yrd.farm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {

	@Autowired
    private MqttPushClient mqttPushClient;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		 mqttPushClient.subscribe("$SYS/brokers/+/clients/+/connected");
         mqttPushClient.subscribe("$SYS/brokers/+/clients/+/disconnected");
         mqttPushClient.subscribe("/user/#");
         mqttPushClient.subscribe("/dev/#");
	}

}
