package com.taskflow.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(
                name = "user_id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "role_id"
        )
    )
    private transient Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_group",
        joinColumns = @JoinColumn(
                name = "user_id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "group_id"
        )
    )
    private transient Set<PermissionGroup> permissionGroups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(
            role ->{
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
                role.getPermissions().forEach(
                        permission -> authorities.add(
                                new SimpleGrantedAuthority(permission.getSubject() + ":" + permission.getAction())
                        )
                );
            }
        );
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
