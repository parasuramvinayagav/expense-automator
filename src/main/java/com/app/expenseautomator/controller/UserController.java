package com.app.expenseautomator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.expenseautomator.entity.User;
import com.app.expenseautomator.services.UserService;

@RestController
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    
    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }
}
