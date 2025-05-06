package com.expenseTracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.expenseTracker.dto.ExpenseDto;
import com.expenseTracker.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody ExpenseDto expenseDto) {
        Long expenseId = expenseService.addExpense(expenseDto);

        return ResponseEntity.ok(String.format("Expense (id: %s) created successfully", expenseId));
    }

    @PutMapping
    public ResponseEntity<String> updateExpense(@RequestBody ExpenseDto expenseDto) {
        Long expenseId = expenseService.updateExpense(expenseDto);

        return ResponseEntity.ok(String.format("Expense (id %s) updated successfully", expenseId));
    }

    @GetMapping
    public List<ExpenseDto> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpense(@PathVariable Long id) {
        return expenseService.getExpense(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok(String.format("Expense (id %s) deleted successfully", id));
    }
}
