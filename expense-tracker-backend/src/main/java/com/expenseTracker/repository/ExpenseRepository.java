package com.expenseTracker.repository;

import java.util.List;
import java.util.Optional;

import com.expenseTracker.model.Expense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByOwnerUsername(String username);

    Optional<Expense> findByIdAndOwnerUsername(Long id, String username);
}
