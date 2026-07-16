package com.app.expenseautomator.services;

import org.springframework.stereotype.Service;

import com.app.expenseautomator.entity.User;
import com.app.expenseautomator.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User saveUser(User user) {
        repository.save(user);
        return user;
    }
    
}
