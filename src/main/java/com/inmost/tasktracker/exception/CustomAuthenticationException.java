package com.inmost.tasktracker.exception;

public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String message, Exception e) {
        super(message);
    }
}
