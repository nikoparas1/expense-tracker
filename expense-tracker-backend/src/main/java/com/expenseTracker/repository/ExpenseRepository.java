package com.expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenseTracker.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
}
