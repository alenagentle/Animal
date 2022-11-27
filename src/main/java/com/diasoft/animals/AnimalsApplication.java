package com.diasoft.animals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnimalsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }
}
