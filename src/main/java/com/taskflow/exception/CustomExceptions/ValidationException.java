package com.taskflow.exception.CustomExceptions;

import com.taskflow.utils.ErrorMessage;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationException extends Throwable {
    private List<ErrorMessage> errors;
}
