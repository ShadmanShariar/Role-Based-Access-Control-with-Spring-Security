package com.example.rbac.util;

import com.example.rbac.model.Role;
import com.example.rbac.model.User;
import com.example.rbac.repository.RoleRepository;
import com.example.rbac.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DummyDataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DummyDataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }

        if (userRepository.count() == 0) {
            Role admin = roleRepository.findAll()
                    .stream().filter(r -> r.getName().equals("ADMIN")).findFirst().orElseThrow();
            Role user = roleRepository.findAll()
                    .stream().filter(r -> r.getName().equals("USER")).findFirst().orElseThrow();

            User testUser = new User();
            testUser.setUsername("testuser");
            testUser.setPassword(passwordEncoder.encode("password")); // store hashed password
            testUser.setRoleIds(Set.of(admin.getId(), user.getId())); // store role IDs as strings

            userRepository.save(testUser);
        }
    }
}
