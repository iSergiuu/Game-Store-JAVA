package com.gamestore.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone; // <--- 1. Asigură-te că ai linia asta importată

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SpringApplication.run(BackendApplication.class, args);
    }

}