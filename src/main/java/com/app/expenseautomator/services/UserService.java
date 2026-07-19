package com.app.expenseautomator.services;

import org.springframework.stereotype.Service;

import com.app.expenseautomator.dtos.UserDto;
import com.app.expenseautomator.entity.User;
import com.app.expenseautomator.exceptions.UserAlreadyExistsException;
import com.app.expenseautomator.exceptions.UserNotFoundException;
import com.app.expenseautomator.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User registerUser(User user) {
        String userEmail = user.getEmail();
        if (repository.existsByEmail(userEmail)) {
            throw new UserAlreadyExistsException(userEmail);
        }

        repository.save(user);
        return user;
    }

    public UserDto getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }
    
}
