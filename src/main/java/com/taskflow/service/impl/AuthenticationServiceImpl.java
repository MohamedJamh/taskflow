package com.taskflow.service.impl;

import com.taskflow.domain.entity.User;
import com.taskflow.exception.customexceptions.BadRequestException;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.AuthenticationService;
import com.taskflow.service.JwtService;
import com.taskflow.utils.ErrorMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    public String signup(User user) throws ValidationException {
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
        return jwtService.generateToken(user);
    }

    @Override
    public String signin(User user) throws BadRequestException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        var optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isEmpty())
            throw new BadCredentialsException("Invalid email or password");
        return jwtService.generateToken(optionalUser.get());
    }
}
