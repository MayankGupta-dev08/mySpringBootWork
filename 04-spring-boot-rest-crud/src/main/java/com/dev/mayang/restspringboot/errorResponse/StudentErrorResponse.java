package com.dev.mayang.restspringboot.errorResponse;

import java.sql.Timestamp;

public class StudentErrorResponse {

    private int statusCode;
    private String status;
    private String message;
    private Timestamp timestamp;

    public StudentErrorResponse() {
    }

    public StudentErrorResponse(int statusCode, String status, String message, Timestamp timestamp) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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