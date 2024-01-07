package com.taskflow.domain.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagRequestDto {
    private String name;
}
