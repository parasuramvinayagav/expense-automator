package com.app.expenseautomator.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException (String email) {
        String exceptionMessage = String.format("User '%s' already exists in the system.", email);
        super(exceptionMessage);
    }
}
