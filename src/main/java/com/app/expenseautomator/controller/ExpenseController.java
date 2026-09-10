package com.app.expenseautomator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.expenseautomator.dtos.PayloadWrapper;
import com.app.expenseautomator.dtos.expense.CreateExpenseRequest;
import com.app.expenseautomator.dtos.expense.ExpenseResponse;
import com.app.expenseautomator.services.ExpenseService;
import com.app.expenseautomator.entity.Expense;

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

    @GetMapping ("/expenses")
    public PayloadWrapper listExpenses() {
        List<Expense> expenseList = service.listAuthUserExpenses();

        return new PayloadWrapper(expenseList.stream()
        .map((Expense expense) -> new ExpenseResponse(expense))
        .toList());
    }
}
