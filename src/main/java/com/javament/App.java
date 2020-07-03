package com.javament;

import com.javament.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public App(UserService userService){
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userService.getSacredKey());
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
