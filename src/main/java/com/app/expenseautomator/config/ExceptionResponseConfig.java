package com.app.expenseautomator.config;

import java.time.Instant;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.app.expenseautomator.exceptions.UserAlreadyExistsException;
import com.app.expenseautomator.exceptions.UserNotFoundException;

@RestControllerAdvice
public class ExceptionResponseConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return getProblemTemplate(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
        return getProblemTemplate(HttpStatus.NOT_FOUND, ex);
    }

    public ProblemDetail getProblemTemplate(HttpStatusCode status, Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("message", ex.getMessage());
        properties.put("timestamp", Instant.now());
        problemDetail.setProperties(properties);

        return problemDetail;
    }
}
