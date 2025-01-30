package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //to enable component scanning interface that are declared FeignClients
public class ExaltHexagonalArchiKafkaKeycloakBsMsOperationApiApplication {

	public static void main(String[] args) {
		new SpringApplication(ExaltHexagonalArchiKafkaKeycloakBsMsOperationApiApplication.class)
				.run(args);
	}

}
