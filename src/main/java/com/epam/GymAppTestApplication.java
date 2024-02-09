package com.epam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class GymAppTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymAppTestApplication.class, args);
    }
    @Bean
    HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }}
