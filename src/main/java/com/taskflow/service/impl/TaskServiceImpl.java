package com.taskflow.service.impl;

import com.taskflow.domain.entity.Task;
import com.taskflow.domain.entity.User;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.TaskService;
import com.taskflow.utils.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private List<ErrorMessage> errors;
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) throws ValidationException {
        if(task.getStartDate().isAfter(LocalDateTime.now().plusDays(3)))
            errors.add(
                    ErrorMessage.builder()
                    .field("Start Date")
                    .message("Start date must be less than 3 days from now")
                    .build()
            );
        User assignedByUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //todo: fetch the user from db and check if he is a manager
        //todo: check the assigned to user
        //todo: fetch tags from db
        if(( ! assignedByUser.getId().equals(task.getAssignedTo().getId() )) &&
              !  SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("MANAGER")
        )
            errors.add(
                    ErrorMessage.builder()
                            .field("Assigned To")
                            .message("You can't assign task to other users")
                            .build()
            );
        if( ! errors.isEmpty())
            throw new ValidationException(errors);
        //todo: build the task object to save
        return taskRepository.save(task);
    }
}
