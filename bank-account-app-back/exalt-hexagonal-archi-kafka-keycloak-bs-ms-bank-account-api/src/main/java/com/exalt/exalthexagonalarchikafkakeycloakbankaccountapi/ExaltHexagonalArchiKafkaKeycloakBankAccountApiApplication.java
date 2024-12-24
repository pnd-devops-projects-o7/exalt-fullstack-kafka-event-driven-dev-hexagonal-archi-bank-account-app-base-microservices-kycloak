package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExaltHexagonalArchiKafkaKeycloakBankAccountApiApplication {

    public static void main(String[] args) {
        new SpringApplication(ExaltHexagonalArchiKafkaKeycloakBankAccountApiApplication.class)
                .run(args);
    }
}
