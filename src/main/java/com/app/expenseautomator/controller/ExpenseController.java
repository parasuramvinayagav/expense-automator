package com.app.expenseautomator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.expenseautomator.dtos.expense.CreateExpenseRequest;
import com.app.expenseautomator.dtos.expense.ExpenseResponse;
import com.app.expenseautomator.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    
    private ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ExpenseResponse createExpense(@Valid @RequestBody CreateExpenseRequest request) {
        ExpenseResponse response = new ExpenseResponse(service.createExpense(request));
        return response;
    }
}
