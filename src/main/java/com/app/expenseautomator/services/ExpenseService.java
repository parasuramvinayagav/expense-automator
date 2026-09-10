package com.app.expenseautomator.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.expenseautomator.dtos.expense.CreateExpenseRequest;
import com.app.expenseautomator.entity.Expense;
import com.app.expenseautomator.entity.User;
import com.app.expenseautomator.enums.ExpenseType;
import com.app.expenseautomator.exceptions.UserNotFoundException;
import com.app.expenseautomator.repositories.ExpenseRepository;

@Service
public class ExpenseService {
    
    private ExpenseRepository repository;
    private UserService userService;

    public ExpenseService(ExpenseRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public User getAuthUser() {
        return userService.getAuthenticatedUser();
    }

    public Expense createExpense(CreateExpenseRequest request) {
        Expense expense = new Expense();
        expense.setName(request.getName());
        LocalDateTime startTime = request.getStartTime().atStartOfDay();
        expense.setStartTime(startTime);

        LocalDate endTime = request.getEndTime();
        if (!ObjectUtils.isEmpty(endTime)) {
            expense.setEndTime(endTime.atTime(LocalTime.MAX));
        }

        expense.setUser(getAuthUser());
        expense.setExpenseType(ExpenseType.valueOf(request.getExpenseType()));
        expense.setValue(request.getValue());
        return repository.save(expense);
    }

    public List<Expense> listAuthUserExpenses() {
        return repository.findByUser(getAuthUser());
    }
}
