package com.ecommerce.project.service;

import com.ecommerce.project.model.SocialUser;

import java.util.List;

public interface SocialService {
    List<SocialUser> getAllUsers();
    SocialUser saveUser(SocialUser socialUser);
    String deleteUser(Long id);
}
