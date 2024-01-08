package com.taskflow._db.seeder.seeds;


import com.taskflow.domain.entity.Permission;
import com.taskflow.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PermissionSeeder {
    private final PermissionRepository permissionRepository;

    private final Set<String> subjects = Set.of(
            "user",
            "role",
            "task",
            "tag"
    ); // add your subjects here
    private final Set<String> actions = Set.of(
            "create",
            "read",
            "update",
            "delete"
    ); // add your actions here
    private final Set<String> uniquePermissions = Set.of(
            "all:manage",
            "assign:task"
    ); // add your unique permissions here

    public void seed() {
        if(permissionRepository.count() == 0){
            subjects.forEach(subject ->
                actions.forEach(action ->
                    permissionRepository.save(
                        Permission.builder()
                                .subject(subject)
                                .action(action)
                                .build()
                    )
                )
            );
            uniquePermissions.forEach(permission ->
                permissionRepository.save(
                    Permission.builder()
                        .subject(permission.split(":")[0])
                        .action(permission.split(":")[1])
                        .build()
                )
            );
        }
    }
}
