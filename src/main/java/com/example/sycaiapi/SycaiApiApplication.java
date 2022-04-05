package com.example.sycaiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SycaiApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SycaiApiApplication.class, args);
    }
}