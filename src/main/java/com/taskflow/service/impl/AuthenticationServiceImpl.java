package com.taskflow.service.impl;

import com.taskflow.domain.dto.request.SignUpRequestDto;
import com.taskflow.domain.dto.request.SigninRequestDto;
import com.taskflow.domain.dto.response.JwtAuthenticationResponseDto;
import com.taskflow.domain.entity.User;
import com.taskflow.domain.enums.Role;
import com.taskflow.exception.CustomExceptions.BadRequestException;
import com.taskflow.exception.CustomExceptions.ValidationException;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.AuthenticationService;
import com.taskflow.service.JwtService;
import com.taskflow.utils.ErrorMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public JwtAuthenticationResponseDto signup(User user) throws ValidationException {
        // this logic to controller
        /*
        var user = User.builder()
                .firstName(signUpRequestDto.getFirstName())
                .lastName(signUpRequestDto.getLastName())
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .role(Role.USER).build();*/
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ValidationException(
                    List.of(
                            ErrorMessage.builder()
                                    .field("email")
                                    .message("Email already exists")
                                    .build()
                    )
            );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder()
                .token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponseDto signin(User user) throws BadRequestException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        var optionalUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));
        var jwt = jwtService.generateToken(optionalUser);
        return JwtAuthenticationResponseDto.builder()
                .token(jwt).build();
    }
}
