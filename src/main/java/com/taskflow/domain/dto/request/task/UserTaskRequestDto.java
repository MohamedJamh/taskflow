package com.taskflow.domain.dto.request.task;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTaskRequestDto {
    @NotNull(message = "User is required")
    private Long id;
}
