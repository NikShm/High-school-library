package com.HighSchoolLibrary.exceptions;


import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/*
@author Микола
@project High_school_library
@class ApiException
@version 1.0.0
@since 26.09.2022 - 21.34
*/
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String message,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp) {

        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", timestamp=" + timestamp +
                '}';
    }
}
