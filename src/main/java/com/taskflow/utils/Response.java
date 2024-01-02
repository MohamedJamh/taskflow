package com.taskflow.utils;

import lombok.*;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response<T> {
    private String message;
    private T result;
    private List<ErrorMessage> errors;
}
