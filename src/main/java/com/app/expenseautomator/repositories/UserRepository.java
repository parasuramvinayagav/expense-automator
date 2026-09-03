package com.app.expenseautomator.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.expenseautomator.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Boolean existsByEmail(String email);

    public Optional<User> findByEmail(String username);
    
}
