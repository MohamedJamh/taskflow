package com.taskflow._db.seeder.seeds;
import com.taskflow.domain.entity.Role;
import com.taskflow.domain.entity.User;
import com.taskflow.repository.RoleRepository;
import com.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Component
public class UserSeeder {

    //the idea behind this is to have a super admin user that you can use in your security level
    //to grant full access
    //without permissions checks
    //in order for this seed to work you need to have a role with name SUPER_ADMIN
    //or your desired super role name
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeeder(
            UserRepository userRepository,
            RoleRepository roleRepository,
            @Qualifier("bcryptPasswordEncoder") PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${seeder.super-admin.password}")
    private String userPassword;
    private final User superAdmin = User.builder()
            .firstName("Super")
            .lastName("Admin")
            .email("super@admin.co")
            .build();
    @Transactional
    public void seed() {
        if(userRepository.count() == 0){
            String superRoleName = "SUPER_ADMIN";
            Optional<Role> superRole = roleRepository.findByName(superRoleName);
            if(superRole.isPresent()){
                String encodedPassword = passwordEncoder.encode(userPassword);
                superAdmin.setPassword(encodedPassword);
                superAdmin.setRoles(Set.of(superRole.get()));
                userRepository.save(superAdmin);
            }
        }
    }
}
