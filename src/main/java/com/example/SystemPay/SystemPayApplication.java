package com.example.SystemPay;

import com.example.SystemPay.config.KafkaConfig;
import com.example.SystemPay.service.KafkaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemPayApplication {

	public static void main(String[] args) {


		SpringApplication.run(SystemPayApplication.class, args);
	}

}
