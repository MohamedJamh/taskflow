package com.taskflow._db.seeder;


import com.taskflow._db.seeder.seeds.PermissionSeeder;
import com.taskflow._db.seeder.seeds.RoleSeeder;
import com.taskflow._db.seeder.seeds.UserSeeder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppSeeder {
    private final PermissionSeeder permissionSeeder;
    private final RoleSeeder roleSeeder;
    private final UserSeeder userSeeder;
    @PostConstruct
    public void init() {
        permissionSeeder.seed();
        roleSeeder.seed();
        userSeeder.seed();
    }
}
