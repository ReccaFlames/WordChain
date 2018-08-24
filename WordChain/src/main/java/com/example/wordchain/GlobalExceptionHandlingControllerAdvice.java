package com.example.wordchain;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.UnexpectedException;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalParameters(UnexpectedException exception) {

    }
}
