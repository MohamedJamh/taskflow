package com.taskflow.domain.dto.request.tag;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagRequestDto {
    private String name;
}
