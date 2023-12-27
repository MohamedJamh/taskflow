package com.taskflow.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject; //entity or resource
    private String action; //create, read, update, delete

    /*@ManyToMany(mappedBy = "permissions")
    @JsonIgnoreProperties("permissions")
    private List<Role> roles;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnoreProperties("permissions")
    private Set<PermissionGroup> groups;*/
}
