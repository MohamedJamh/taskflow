package com.taskflow.domain.dto.request.tag;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagRequestDto {
    @NotNull(message = "Tag Name cannot be null")
    private String name;
}
