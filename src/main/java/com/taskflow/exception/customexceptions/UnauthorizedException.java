package com.taskflow.exception.customexceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnauthorizedException extends RuntimeException {
    private final String message;
}
