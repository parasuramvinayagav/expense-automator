package com.app.expenseautomator.dtos.user;

import com.app.expenseautomator.entity.User;

public class UserResponse {
    
    private Long id;
    private String email;
    private String name;

    public UserResponse(User user) {
        id = user.getId();
        email = user.getEmail();
        name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
