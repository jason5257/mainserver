package com.mainserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainserverApplication {

    public static void main(String[] args) {
        System.out.println("App Running...");
        SpringApplication.run(MainserverApplication.class, args);
    }

}
