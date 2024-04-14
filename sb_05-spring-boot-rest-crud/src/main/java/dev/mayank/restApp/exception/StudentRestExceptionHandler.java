package dev.mayank.restApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

// @ExceptionHandler for handling student exception
@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleSNFException(StudentNotFoundException exception) {
        StudentErrorResponse errorResp = new StudentErrorResponse();
        errorResp.setStatus(HttpStatus.NOT_FOUND.value());
        errorResp.setError(HttpStatus.NOT_FOUND.name());
        errorResp.setMessage(exception.getMessage());
        errorResp.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleGenericException(Exception exception) {
        StudentErrorResponse errorResp = new StudentErrorResponse();
        errorResp.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResp.setError(HttpStatus.BAD_REQUEST.name());
        errorResp.setMessage(exception.getMessage());
        errorResp.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }
}