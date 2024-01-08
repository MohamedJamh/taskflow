package com.taskflow.domain.dto.response.auth;

import com.taskflow.domain.dto.response.user.UserResponseDto;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtAuthenticationResponseDto {
    private String accessToken;
    private String refreshToken;
    private UserResponseDto user;
}
