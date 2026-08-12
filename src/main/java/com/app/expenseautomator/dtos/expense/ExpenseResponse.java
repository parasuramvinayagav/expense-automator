package com.app.expenseautomator.dtos.expense;

import java.time.LocalDate;

import com.app.expenseautomator.entity.Expense;
import com.app.expenseautomator.enums.ExpenseType;

public class ExpenseResponse {
    
    private Long id;
    private String name;
    private ExpenseType expenseType;
    private Float value;
    private LocalDate startTime;
    private LocalDate endTime;
    private LocalDate createdAt;

    public ExpenseResponse(Expense expense) {
        id = expense.getId();
        name = expense.getName();
        expenseType = expense.getExpenseType();
        value = expense.getValue();
        startTime = expense.getStartTime().toLocalDate();
        endTime = expense.getEndTime().toLocalDate();
        createdAt = expense.getCreatedAt().toLocalDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Float getValue() {
        return value;
    }
}
