package com.taskflow.domain.dto.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshTokenResponseDTO {
    private String accessToken;
}
