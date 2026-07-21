package com.app.expenseautomator.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.expenseautomator.dtos.user.UserDto;
import com.app.expenseautomator.dtos.user.UserUpdateRequestDto;
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

    public User getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    public User updateUserById(Long id, UserUpdateRequestDto updateRequest) {
        User user = getUserById(id);
        user.setName(updateRequest.name());
        Optional<String> email = updateRequest.email();

        if (email.isPresent()) {
            user.setEmail(email.get());
        }

        return repository.save(user);
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }
    
}
