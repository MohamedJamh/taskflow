package com.taskflow.web.rest;

import com.taskflow.domain.dto.request.SignUpRequestDto;
import com.taskflow.domain.dto.request.SigninRequestDto;
import com.taskflow.domain.dto.response.JwtAuthenticationResponseDto;
import com.taskflow.domain.entity.User;
import com.taskflow.domain.enums.Role;
import com.taskflow.exception.customexceptions.BadRequestException;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.service.AuthenticationService;
import com.taskflow.utils.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRest {
    private final AuthenticationService authenticationService;
    public AuthRest(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Response<JwtAuthenticationResponseDto>> signup(@RequestBody @Valid SignUpRequestDto signUpRequestDto) throws ValidationException {
        Response<JwtAuthenticationResponseDto> response = new Response<>();
        User user = User.builder()
                .firstName(signUpRequestDto.getFirstName())
                .lastName(signUpRequestDto.getLastName())
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .role(Role.USER).build();
        response.setResult(authenticationService.signup(user));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Response<JwtAuthenticationResponseDto>> signin(@RequestBody @Valid SigninRequestDto signinRequestDto) throws BadRequestException {
        Response<JwtAuthenticationResponseDto> response = new Response<>();
        User user = User.builder()
                .email(signinRequestDto.getEmail())
                .password(signinRequestDto.getPassword())
                .build();
        response.setResult(authenticationService.signin(user));
        return ResponseEntity.ok().body(response);
    }
}
