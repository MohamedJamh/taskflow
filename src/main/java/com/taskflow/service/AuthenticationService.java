package com.taskflow.service;

import com.taskflow.domain.dto.request.SignUpRequestDto;
import com.taskflow.domain.dto.request.SigninRequestDto;
import com.taskflow.domain.dto.response.JwtAuthenticationResponseDto;
import com.taskflow.domain.entity.User;
import com.taskflow.exception.CustomExceptions.BadRequestException;
import com.taskflow.exception.CustomExceptions.ValidationException;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponseDto signup(User request) throws ValidationException;
    JwtAuthenticationResponseDto signin(User request) throws BadRequestException;
}
