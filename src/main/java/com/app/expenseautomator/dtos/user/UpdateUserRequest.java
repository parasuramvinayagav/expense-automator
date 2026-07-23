package com.app.expenseautomator.dtos.user;

import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    @Size(min = 4, max = 15, message = "Password should not be less than 4 and more than 8 characters")
    private String password;

    @Size(min = 3, max = 50, message = "User name should not be less than 3 and more than 50 characters")
    private String name;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
