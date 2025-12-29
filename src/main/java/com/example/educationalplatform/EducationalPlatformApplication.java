package com.example.educationalplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.educationalplatform")
public class EducationalPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(EducationalPlatformApplication.class, args);
    }
}
