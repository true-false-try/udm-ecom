package com.ecommerce.project.model;

;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGeneratorId", allocationSize = 5)
    private Long id;

    @OneToOne(mappedBy = "socialUser", cascade = CascadeType.ALL )
    private SocialProfile socialProfile;

    @JsonIgnore
    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "social_user_id"),
            inverseJoinColumns = @JoinColumn(name =  "group_id")
    )
    private Set<SocialGroup> socialGroups = new HashSet<>();
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        socialProfile.setSocialUser(this);
        this.socialProfile = socialProfile;
    }
}
