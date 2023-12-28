package com.taskflow.web.rest;

import com.taskflow.domain.entity.User;
import com.taskflow.repository.UserRepository;
import com.taskflow.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
@RequiredArgsConstructor
public class UserRest {
    private final UserRepository userRepository;

    @PutMapping("/update")
    public ResponseEntity<Response<User>> updateUser(@RequestBody User updatedUser) {
        Response<User> response = new Response<>();
        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            updatedUser.setRoles(user.getRoles());
            updatedUser.setPermissionGroups(user.getPermissionGroups());
            userRepository.save(updatedUser);
            response.setMessage("User updated successfully");
            response.setResult(updatedUser);
        }else{
            response.setMessage("User not found");
        }
        return ResponseEntity.ok().body(response);
    }
}
