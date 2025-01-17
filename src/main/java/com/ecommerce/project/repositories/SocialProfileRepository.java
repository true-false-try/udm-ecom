package com.ecommerce.project.repositories;

import com.ecommerce.project.model.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile,Long> {
}
