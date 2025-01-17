package com.ecommerce.project.repositories;

import com.ecommerce.project.model.SocialProfile;
import com.ecommerce.project.model.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser,Long> {

}
