package com.app.expenseautomator.dtos.user;

import java.time.LocalDateTime;
import java.time.LocalDate;

import com.app.expenseautomator.entity.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "email", "createdAt"})
public class UserResponse {
    
    private Long id;
    private String email;
    private String name;
    private LocalDateTime createdAt;

    public UserResponse(User user) {
        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        createdAt = user.getCreatedAt();
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

    public LocalDate getCreatedAt() {
        return createdAt.toLocalDate();
    }
}
