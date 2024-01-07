package com.taskflow.web.rest;

import com.taskflow.domain.dto.request.task.TaskRequestDto;
import com.taskflow.domain.dto.response.task.TaskResponseDto;
import com.taskflow.domain.entity.Task;
import com.taskflow.domain.entity.User;
import com.taskflow.domain.mapper.TaskMapper;
import com.taskflow.domain.mapper.UserMapper;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.service.TaskService;
import com.taskflow.utils.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskRest {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<Response<TaskResponseDto>> createTask(
            @RequestBody TaskRequestDto taskRequestDto
    ) throws ValidationException {
        Response<TaskResponseDto> response = new Response<>();
        Task saveTask = taskService.createTask(
                taskMapper.toTask(taskRequestDto)
        );
        response.setResult(taskMapper.toDto(saveTask));
        response.setMessage("Task created successfully");
        return ResponseEntity.ok().body(response);
    }
}
