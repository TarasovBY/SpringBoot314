package com.javament.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;



@Configuration
public class RestConfig {

    @Bean
    public RestTemplate generateRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://91.241.64.178:7081/api/users"));
        return restTemplate;
    }





}
