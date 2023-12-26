package com.taskflow.exception;

import com.taskflow.exception.customexceptions.BadRequestException;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.utils.ErrorMessage;
import com.taskflow.utils.Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<List<ErrorMessage>>> inputValidationException(MethodArgumentNotValidException ex) {
        List<ErrorMessage> errorMessages = new ArrayList<>();
        Response<List<ErrorMessage>> response = new Response<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            String errorField = error.getField();
            ErrorMessage errorMessageObj = ErrorMessage.builder()
                    .field(errorField)
                    .message(errorMessage).build();
            errorMessages.add(errorMessageObj);
        });
        response.setMessage("Validation error");
        response.setErrors(errorMessages);
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<ErrorMessage>> inputValidationException(ValidationException ex) {
        Response<ErrorMessage> response = new Response<>();
        response.setMessage("Validation error");
        response.setErrors(ex.getErrors());
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<String>> inputValidationException(BadRequestException ex) {
        Response<String> response = new Response<>();
        response.setMessage(ex.getMessage());
        response.setErrors(null);
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<String>> userNotFound(UsernameNotFoundException ex) {
        Response<String> response = new Response<>();
        response.setMessage("User not found");
        response.setErrors(
                List.of(
                    ErrorMessage.builder()
                            .field("email")
                            .message("User with associated email not found")
                            .build()
                )
        );
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<String>> badCredentials(BadCredentialsException ex) {
        Response<String> response = new Response<>();
        response.setMessage("Invalid email or password");
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Response<String>> unauthorized(ExpiredJwtException ex) {
        Response<String> response = new Response<>();
        response.setMessage("Expired token");
        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED
        );
    }
}
