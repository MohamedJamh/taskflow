package com.taskflow.domain.dto.response.tag;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TagResponseDto {
    private Long id;
    private String name;
}
