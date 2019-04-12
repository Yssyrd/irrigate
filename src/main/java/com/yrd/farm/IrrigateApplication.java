package com.yrd.farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.yrd.farm.config.Constant;

@SpringBootApplication
@EnableConfigurationProperties(Constant.class)
public class IrrigateApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrrigateApplication.class, args);
	}

}

