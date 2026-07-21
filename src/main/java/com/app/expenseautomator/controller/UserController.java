package com.app.expenseautomator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.expenseautomator.dtos.user.UserDto;
import com.app.expenseautomator.dtos.user.UserUpdateRequestDto;
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
        return service.registerUser(user);
    }

    @GetMapping("user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        User user = service.getUserById(id);
        return service.toDto(user);
    }

    @PatchMapping("user/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto updateRequest) {
        User user = service.updateUserById(id, updateRequest);
        return service.toDto(user);
    }
}
