package com.taskflow.service.impl;

import com.taskflow.domain.dto.response.auth.JwtAuthenticationResponseDto;
import com.taskflow.domain.entity.User;
import com.taskflow.domain.mapper.UserMapper;
import com.taskflow.exception.customexceptions.BadRequestException;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.repository.RoleRepository;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.AuthenticationService;
import com.taskflow.service.JwtService;
import com.taskflow.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            @Qualifier("bcryptPasswordEncoder")
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            RoleRepository roleRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }
    @Override
    @Transactional
    public JwtAuthenticationResponseDto signup(User user) throws ValidationException {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ValidationException(
                    List.of(
                            ErrorMessage.builder()
                                    .field("email")
                                    .message("Email already exists")
                                    .build()
                    )
            );
        roleRepository.findByName("USER").ifPresent(role -> user.setRoles(Set.of(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return JwtAuthenticationResponseDto.builder()
                .accessToken(jwtService.generateToken(user))
                .user(userMapper.toDto(user))
                .build();
    }

    @Override
    public JwtAuthenticationResponseDto signin(User user) throws BadRequestException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        var optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isEmpty())
            throw new BadCredentialsException("Invalid email or password");
        return JwtAuthenticationResponseDto.builder()
                .accessToken(jwtService.generateToken(user))
                .user(userMapper.toDto(optionalUser.get()))
                .build();
    }
}
