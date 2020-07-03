package com.javament.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import org.springframework.web.client.RestTemplate;


@Configuration
public class RestConfig {

    @Bean
    public RestTemplate generateRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Gson generateGson(){
        return new Gson();
    }

    @Bean
    public HttpHeaders generateHeaders(){
        return new HttpHeaders();
    }




}
