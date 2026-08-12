package com.app.expenseautomator.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
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

        User user = null;
        if (user == null) {
            throw new UserNotFoundException();
        }

        expense.setExpenseType(ExpenseType.valueOf(request.getExpenseType()));
        expense.setValue(request.getValue());
        return repository.save(expense);
    }
    
}
