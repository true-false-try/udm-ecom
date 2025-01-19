package com.ecommerce.project.service.impl;

import com.ecommerce.project.controller.SocialController;
import com.ecommerce.project.model.SocialUser;
import com.ecommerce.project.repositories.SocialUserRepository;
import com.ecommerce.project.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {
    private final SocialUserRepository socialUserRepository;
    @Override
    public List<SocialUser> getAllUsers() {
        return socialUserRepository.findAll();
    }

    @Override
    public SocialUser saveUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }

    @Override
    public String deleteUser(Long id) {
        SocialUser socialUser = socialUserRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        socialUserRepository.delete(socialUser);
        return socialUser.toString();
    }
}
