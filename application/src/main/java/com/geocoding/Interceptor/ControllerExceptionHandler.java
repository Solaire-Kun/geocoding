package com.geocoding.Interceptor;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<String> handleConflict(Exception e, HttpServletResponse response) {
        /*if(e instanceof DataIntegrityViolationException) {
            if(e.getCause().getCause().getMessage().contains("Duplicate entry")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This entry already exists.");
            }
        }*/
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
