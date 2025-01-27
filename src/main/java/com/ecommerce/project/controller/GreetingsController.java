package com.ecommerce.project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String  userEndpoint() {
        return "Hello, USER";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String  adminEndpoint() {
        return "Hello,  ADMIN";
    }
}
