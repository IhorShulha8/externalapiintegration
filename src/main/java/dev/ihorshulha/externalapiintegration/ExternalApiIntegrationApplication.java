package dev.ihorshulha.externalapiintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "dev.ihorshulha.externalapiintegration.api")
@SpringBootApplication
public class ExternalApiIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalApiIntegrationApplication.class, args);
    }

}
