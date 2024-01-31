package com.example.customerservice.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(ConstraintViolationException exception){
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        Map<String, List<String>> listMap=new HashMap<>();
        constraintViolations.forEach(cv->{
            List<String> fieldErrors = listMap.get(cv.getPropertyPath().toString());
            if(fieldErrors==null){
                listMap.put(cv.getPropertyPath().toString(),new ArrayList<>());
            }
            listMap.get(cv.getPropertyPath().toString()).add(cv.getMessage());
        });
        return ResponseEntity.badRequest().body(listMap);
    }
}
