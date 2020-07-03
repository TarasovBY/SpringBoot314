package com.javament.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javament.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final Gson gson;
    private final HttpHeaders headers;
    private String sacredKey = "";
    private final String uri = "http://91.241.64.178:7081/api/users";
    private final Type type = new TypeToken<List<User>>() {}.getType();
    private List<User> listUser;
    List<String> setCookies;


    @Autowired
    public UserService(RestTemplate restTemplate, Gson gson, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.gson = gson;
        this.headers = headers;
    }

    public void setUserAndCookies(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        listUser = gson.fromJson(forEntity.getBody(), type);
        setCookies = forEntity.getHeaders().get("Set-Cookie");
    }

    public void setSacredKeyOne() {
        listUser.add(new User((long)3,"James", "Brown", (byte)24));
        headers.set("Cookie", setCookies.get(0));
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(listUser.get(2)),headers);
        ResponseEntity<String> forEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        sacredKey = sacredKey + forEntity.getBody();
    }

    public void setSacredKeyTwo(){
        listUser.get(2).setName("Thomas");
        listUser.get(2).setLastName("Shelby");
        HttpEntity<String> entity2 = new HttpEntity<String>(gson.toJson(listUser.get(2)),headers);
        ResponseEntity<String> forEntity = restTemplate.exchange(uri, HttpMethod.PUT, entity2, String.class);
        sacredKey = sacredKey + forEntity.getBody();
    }

    public void setSacredKeyThree() {
        HttpEntity<String> entity3 = new HttpEntity<String>(headers);
        ResponseEntity<String> forEntity = restTemplate.exchange(uri + "/3", HttpMethod.DELETE, entity3, String.class);
        sacredKey = sacredKey + forEntity.getBody();
    }

    public String getSacredKey(){
        setUserAndCookies();
        setSacredKeyOne();
        setSacredKeyTwo();
        setSacredKeyThree();
        return sacredKey;
    }

}
