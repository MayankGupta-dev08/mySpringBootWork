package com.dev.mayang.restspringboot.exception;

import java.sql.Timestamp;

public class StudentErrorResponse {

    private int status;
    private String error;
    private String message;
    private Timestamp timestamp;

    public StudentErrorResponse() {
    }

    public StudentErrorResponse(int status, String error, String message, Timestamp timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}