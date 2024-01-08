package com.taskflow.domain.dto.response.task;

import com.taskflow.domain.dto.response.tag.TagResponseDto;
import com.taskflow.domain.dto.response.user.UserResponseDto;
import com.taskflow.domain.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class TaskResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final LocalDateTime createdAt;
    private final UserResponseDto createdBy;
    private final UserResponseDto assignedBy;
    private final UserResponseDto assignedTo;
    private final TaskStatus status;
    private final Set<TagResponseDto> tags;
}