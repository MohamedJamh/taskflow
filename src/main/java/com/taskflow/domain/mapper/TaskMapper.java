package com.taskflow.domain.mapper;

import com.taskflow.domain.dto.request.task.TaskRequestDto;
import com.taskflow.domain.dto.request.task.UserTaskRequestDto;
import com.taskflow.domain.dto.response.task.TaskResponseDto;
import com.taskflow.domain.entity.Task;
import com.taskflow.domain.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    Task toTask(TaskRequestDto taskResponseDto);
    TaskResponseDto toDto(Task task);
    User toUser(UserTaskRequestDto userDto);
}