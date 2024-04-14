package dev.mayank.restApp.exception;

/**
 * Our custom exception class for StudentNotFound case, which will be used to return StudentErrorResponse using StudentRestExceptionHandler class.
 */
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}