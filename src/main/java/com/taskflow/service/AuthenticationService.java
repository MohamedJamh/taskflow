package com.taskflow.service;

import com.taskflow.domain.entity.User;
import com.taskflow.exception.customexceptions.BadRequestException;
import com.taskflow.exception.customexceptions.ValidationException;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    String signup(User request) throws ValidationException;
    String signin(User request) throws BadRequestException;
}
