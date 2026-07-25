package com.app.expenseautomator.services;

import org.springframework.stereotype.Service;

import com.app.expenseautomator.dtos.user.CreateUserRequest;
import com.app.expenseautomator.dtos.user.UpdateUserRequest;
import com.app.expenseautomator.entity.User;
import com.app.expenseautomator.exceptions.UserAlreadyExistsException;
import com.app.expenseautomator.exceptions.UserNotFoundException;
import com.app.expenseautomator.repositories.UserRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User registerUser(CreateUserRequest user) {
        String userEmail = user.getEmail();
        if (StringUtils.isNotBlank(userEmail) && repository.existsByEmail(userEmail)) {
            throw new UserAlreadyExistsException(userEmail);
        }

        User newUser = new User();
        newUser.setEmail(userEmail);
        newUser.setPassword(user.getPassword());
        String userName = user.getName();
        newUser.setName(StringUtils.isNotBlank(userName) ? userName : newUser.getNameFromEmail());

        return repository.save(newUser);
    }

    public User getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    @Transactional
    public User updateUserById(Long id, UpdateUserRequest updateRequest) {
        User user = getUserById(id);
        String userName = updateRequest.getName();

        if (StringUtils.isNotBlank(userName)) {
            user.setName(userName);
        }

        String password = updateRequest.getPassword();

        if (StringUtils.isNotBlank(password)) {
            user.setPassword(password);
        }

        return repository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        repository.delete(user);
    }
    
}
