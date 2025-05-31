package com.ecommerce.project.config;

import com.ecommerce.project.model.AppRole;
import com.ecommerce.project.model.Role;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repositories.RoleRepository;
import com.ecommerce.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final WebSecurityConfig webSecurityConfig;

    @Override
    public void run(String... args) throws Exception {
        Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                .orElseGet(() -> {
                    Role newUserRole = new Role();
                    newUserRole.setRoleName(AppRole.ROLE_USER);
                    return roleRepository.save(newUserRole);
                });

        Role sellerRole = roleRepository.findByRoleName(AppRole.ROLE_SELLER)
                .orElseGet(() -> {
                    Role newSellerRole = new Role();
                    newSellerRole.setRoleName(AppRole.ROLE_SELLER);
                    return roleRepository.save(newSellerRole);
                });

        Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                .orElseGet(() -> {
                    Role newAdminRole = new Role();
                    newAdminRole.setRoleName(AppRole.ROLE_ADMIN);
                    return roleRepository.save(newAdminRole);
                });

        Set<Role> userRoles = Set.of(userRole);
        Set<Role> sellerRoles = Set.of(sellerRole);
        Set<Role> adminRoles = Set.of(userRole, sellerRole, adminRole);

        if (!userRepository.existsByUsername("user1")) {
            User user1 = new User();
            user1.setUsername("user1");
            user1.setEmail("user1@example.com");
            user1.setPassword(webSecurityConfig.passwordEncoder().encode("password1"));
            user1.setRoles(userRoles);
            userRepository.save(user1);
        }

        if (!userRepository.existsByUsername("seller1")) {
            User seller1 = new User();
            seller1.setUsername("seller1");
            seller1.setEmail("seller1@example.com");
            seller1.setPassword(webSecurityConfig.passwordEncoder().encode("password2"));
            seller1.setRoles(sellerRoles);
            userRepository.save(seller1);
        }

        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(webSecurityConfig.passwordEncoder().encode("adminPass"));
            admin.setRoles(adminRoles);
            userRepository.save(admin);
        }
    }
}
