package com.ecommerce.project.repositories;

import com.ecommerce.project.model.SocialGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialGroupRepository extends JpaRepository<SocialGroup,Long> {
}
