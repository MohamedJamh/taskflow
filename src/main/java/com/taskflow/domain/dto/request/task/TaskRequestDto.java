package com.taskflow.domain.dto.request.task;

import com.taskflow.domain.dto.request.tag.TagRequestDto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.taskflow.domain.entity.Task}
 */
@AllArgsConstructor
@Getter
public class TaskRequestDto implements Serializable {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private final String name;
    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    private final String description;
    @NotNull
    @FutureOrPresent
    private final LocalDateTime startDate;
    @Future
    private final LocalDateTime endDate;
    private final Long assignedToId;
    @NotNull
    private final Set<TagRequestDto> tags;
}