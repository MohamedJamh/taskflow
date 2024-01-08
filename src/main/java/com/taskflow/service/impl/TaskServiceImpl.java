package com.taskflow.service.impl;

import com.taskflow.domain.entity.Tag;
import com.taskflow.domain.entity.Task;
import com.taskflow.domain.entity.User;
import com.taskflow.domain.enums.TaskStatus;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.repository.TagRepository;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.TaskService;
import com.taskflow.utils.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private List<ErrorMessage> errors;
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) throws ValidationException {
        errors.clear();
        if(task.getStartDate().isAfter(LocalDateTime.now().plusDays(3)))
            errors.add(
                    ErrorMessage.builder().field("Start Date").message("Start date must be less than 3 days from now").build()
            );
        if(task.getStartDate().isAfter(task.getEndDate()))
            errors.add(
                    ErrorMessage.builder().field("Start Date").message("Start date must be less than end date").build()
            );
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalAssignedByUser = userRepository.findById(loggedInUser.getId());
        Optional<User> optionalAssignedToUser = userRepository.findById(task.getAssignedTo().getId());
        User assignedByUser = new User();
        User assignedToUser= new User();
        if(optionalAssignedToUser.isEmpty() || optionalAssignedByUser.isEmpty())
            errors.add(
                ErrorMessage.builder().field("User").message("User not found").build()
            );
        else {
            assignedByUser = optionalAssignedByUser.get();
            assignedToUser = optionalAssignedToUser.get();
            if(
                ( ! assignedByUser.getId().equals(task.getAssignedTo().getId()) ) &&
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                    .noneMatch(
                        grantedAuthority -> grantedAuthority.getAuthority().equals("MANAGER") || grantedAuthority.getAuthority().equals("all:manage")
                    )
            )
                errors.add(
                    ErrorMessage.builder().field("Assigned To").message("You can't assign task to other users").build()
                );
        }
        if( ! errors.isEmpty())
            throw new ValidationException(errors);
        Set<Tag> tags = new HashSet<>();
        task.getTags().forEach(tag -> {
            Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
            tagOptional.ifPresent(tags::add);
        });
        task.setStatus(TaskStatus.UNDONE);
        task.setAssignedBy(assignedByUser);
        task.setCreatedBy(assignedByUser);
        task.setAssignedTo(assignedToUser);
        task.setCreatedAt(LocalDateTime.now());
        task.setTags(tags);
        return taskRepository.save(task);
    }
}
