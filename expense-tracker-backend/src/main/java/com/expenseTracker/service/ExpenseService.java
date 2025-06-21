package com.expenseTracker.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.expenseTracker.dto.ExpenseDto;
import com.expenseTracker.model.Expense;
import com.expenseTracker.model.User;
import com.expenseTracker.repository.ExpenseRepository;
import com.expenseTracker.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public Long addExpense(ExpenseDto expenseDto) {
        User owner = getCurrentUser();
        Expense expense = mapFromDto(expenseDto);
        expense.setOwner(owner);
        return expenseRepository.save(expense).getId();
    }

    public Long updateExpense(ExpenseDto expenseDto) {
        if (expenseDto.getId() == null) {
            throw new IllegalArgumentException("Expense ID is required");
        }

        String username = getCurrentUsername();

        Expense savedExpense = expenseRepository
                .findByIdAndOwnerUsername(expenseDto.getId(), username)
                .orElseThrow(() -> new ExpenseNotFoundException(
                        String.format("Cannot find expense by ID %s", expenseDto.getId())));

        savedExpense.setExpenseName(expenseDto.getExpenseName());
        savedExpense.setExpenseCategory(expenseDto.getExpenseCategory());
        savedExpense.setExpenseAmount(expenseDto.getExpenseAmount());
        return expenseRepository.save(savedExpense).getId();
    }

    public ExpenseDto getExpense(Long id) {
        String username = getCurrentUsername();
        Expense expense = expenseRepository
                .findByIdAndOwnerUsername(id, username)
                .orElseThrow(() -> new ExpenseNotFoundException(
                        String.format("Cannot find expense by ID %s", id)));

        return mapToDto(expense);
    }

    public List<ExpenseDto> getAllExpenses() {
        String username = getCurrentUsername();
        return expenseRepository
                .findAllByOwnerUsername(username)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public void deleteExpense(Long id) {
        String username = getCurrentUsername();
        Expense e = expenseRepository
                .findByIdAndOwnerUsername(id, username)
                .orElseThrow(() -> new ExpenseNotFoundException(
                        String.format("Cannot find expense by ID %s", id)));
        expenseRepository.delete(e);
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return auth.getName();
    }

    private User getCurrentUser() {
        return userRepository
                .findByUsername(getCurrentUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Current user not found"));
    }

    private Expense mapFromDto(ExpenseDto expense) {
        return Expense.builder()
                .expenseName(expense.getExpenseName())
                .expenseCategory(expense.getExpenseCategory())
                .expenseAmount(expense.getExpenseAmount()).build();
    }

    private ExpenseDto mapToDto(Expense expense) {
        return ExpenseDto.builder()
                .id(expense.getId())
                .expenseName(expense.getExpenseName())
                .expenseCategory(expense.getExpenseCategory())
                .expenseAmount(expense.getExpenseAmount()).build();
    }
}
