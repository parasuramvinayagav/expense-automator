package com.app.expenseautomator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.expenseautomator.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
}
