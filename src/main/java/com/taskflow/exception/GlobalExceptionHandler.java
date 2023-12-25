package com.taskflow.exception;

import com.taskflow.exception.CustomExceptions.BadRequestException;
import com.taskflow.exception.CustomExceptions.ValidationException;
import com.taskflow.utils.ErrorMessage;
import com.taskflow.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            ErrorMessage errorMessageObj = ErrorMessage.builder().message(errorMessage).build();
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
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
}
