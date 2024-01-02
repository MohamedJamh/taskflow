package com.taskflow.domain.dto.response.jwt;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshTokenResponseDTO {
    private String accessToken;
}
