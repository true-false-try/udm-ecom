package com.ecommerce.project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_user_id")
    private SocialUser socialUser;

    private String description;

    public void setSocialUser(SocialUser socialUser) {
        this.socialUser = socialUser;
        if (socialUser.getSocialProfile() != this) {
            socialUser.setSocialProfile(this);
        }
    }
}
