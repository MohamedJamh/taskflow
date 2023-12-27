package com.taskflow.domain.dto.response;

import lombok.*;

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
    private String role;
}
