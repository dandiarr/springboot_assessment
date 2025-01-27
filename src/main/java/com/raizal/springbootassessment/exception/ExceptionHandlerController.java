package com.raizal.springbootassessment.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {

        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();

        List<String> errors = new ArrayList<>();

        if (!violations.isEmpty()) {
        violations.forEach(violation-> {
            errors.add(violation.getMessage());
        });

        }else {
        errors.add("ConstraintViolationException occurred.");
    }

        Collections.sort(errors);

    // Create a HashMap to store the list of errors error: errors
    Map<String, List<String>> errorResponse = new HashMap<>();

        errorResponse.put("error", errors);

        return new ResponseEntity<>("There are invalid inputs", HttpStatus.BAD_REQUEST);
    }

}
