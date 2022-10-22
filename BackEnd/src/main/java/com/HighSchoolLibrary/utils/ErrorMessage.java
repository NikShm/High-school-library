package com.HighSchoolLibrary.utils;

import java.time.LocalDateTime;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project FreshBeauty
 * @class ErrorMessage
 * @since 8/13/2022 - 22.14
 **/
public class ErrorMessage {
    private int status;
    private String error;
    private LocalDateTime timestamp;
    private String path;
    private String message;

    public ErrorMessage(int status, String error, LocalDateTime timestamp, String path, String message) {
        this.status = status;
        this.error = error;
        this.timestamp = timestamp;
        this.path = path;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
