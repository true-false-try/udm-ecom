package com.ecommerce.project.controller;

import com.ecommerce.project.model.SocialUser;
import com.ecommerce.project.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SocialController {

    private final SocialService service;

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> saveUser(@RequestBody SocialUser socialUser) {
        return ResponseEntity.ok(service.saveUser(socialUser));
    }
}
