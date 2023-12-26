package com.taskflow.exception.customexceptions;

import com.taskflow.utils.ErrorMessage;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ValidationException extends Exception {
    private final transient List<ErrorMessage> errors;
}
