package com.HighSchoolLibrary.controller;


import com.HighSchoolLibrary.exceptions.ApiException;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

/*
@author Микола
@project High_school_library
@class ApiExceptionHandler
@version 1.0.0
@since 26.09.2022 - 21.30
*/
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {DatabaseFetchException.class})
    public ResponseEntity<Object> handleApiRequestException(DatabaseFetchException e){
        final ApiException apiException = new ApiException(e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
