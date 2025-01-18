package com.ecommerce.project;

import com.ecommerce.project.model.SocialUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SbEcomApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbEcomApplication.class, args);
    }

}
