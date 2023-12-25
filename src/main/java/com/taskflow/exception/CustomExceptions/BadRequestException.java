package com.taskflow.exception.CustomExceptions;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends Throwable {
    private String message;
}
