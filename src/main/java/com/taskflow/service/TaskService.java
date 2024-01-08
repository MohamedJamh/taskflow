package com.taskflow.service;

import com.taskflow.domain.entity.Task;
import com.taskflow.exception.customexceptions.ValidationException;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task createTask(Task task) throws ValidationException;
}
