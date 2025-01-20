package com.ecommerce.project.model;

;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SocialProfile socialProfile;

    @JsonIgnore
    @OneToMany(mappedBy = "socialUser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "social_user_id"),
            inverseJoinColumns = @JoinColumn(name =  "group_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SocialGroup> socialGroups = new HashSet<>();
}
