package com.taskflow.domain.dto.response.permission_group;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionGroupDto {
    private Long id;
    private String subject;
    private String action;
}
