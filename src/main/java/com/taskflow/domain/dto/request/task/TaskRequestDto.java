package com.taskflow.domain.dto.request.task;

import com.taskflow.domain.dto.request.tag.TagRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskRequestDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDateTime startDate;
    @Future(message = "End date cannot be in the past")
    private LocalDateTime endDate;
    private UserTaskRequestDto assignedTo;
    @Valid
    @NotNull(message = "Tags cannot be null")
    @Size(min = 1, message = "Tags cannot be empty")
    private List<TagRequestDto> tags;
}