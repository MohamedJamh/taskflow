package com.taskflow.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "groups")
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(nullable = true)
    private LocalDateTime deadline;

    /*@ManyToMany(mappedBy = "permissionGroups")
    private Set<User> users;*/

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "group_permission",
            joinColumns = @JoinColumn(
                    name = "group_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id"
            )
    )
    private Set<Permission> permissions;
}
