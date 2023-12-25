package com.taskflow.domain.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SigninRequestDto {
    private String email;
    private String password;
}
