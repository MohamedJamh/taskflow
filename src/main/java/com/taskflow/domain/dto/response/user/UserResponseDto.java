package com.taskflow.domain.dto.response.user;

import com.taskflow.domain.dto.response.role.RoleResponseDto;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<RoleResponseDto> roles;
}
