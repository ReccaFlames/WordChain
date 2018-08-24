package com.example.wordchain;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {


    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalParameters(Exception exception) {
        return exception.toString();
    }
}
