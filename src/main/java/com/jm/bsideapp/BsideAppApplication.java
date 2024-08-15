package com.jm.bsideapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BsideAppApplication {

    public static void main(String[] args){
        SpringApplication.run(BsideAppApplication.class, args);
    }
}
