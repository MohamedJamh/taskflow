package com.taskflow.web.rest;

import com.taskflow.domain.dto.response.user.UserResponseDto;
import com.taskflow.domain.entity.User;
import com.taskflow.domain.mapper.UserMapper;
import com.taskflow.repository.UserRepository;
import com.taskflow.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
@RequiredArgsConstructor
public class UserRest {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/all")
    public ResponseEntity<Response<List<UserResponseDto>>> getAllUsers() {
        Response<List<UserResponseDto>> response = new Response<>();
        List<User> users = userRepository.findAll();
        response.setResult(
                users.stream()
                        .map(userMapper::toDto)
                        .toList()
        );
        response.setMessage("Users retrieved successfully");
        return ResponseEntity.ok().body(response);
    }
}
