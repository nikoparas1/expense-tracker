package com.expenseTracker.service;

public class ExpenseNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2749976220271966829L;

    public ExpenseNotFoundException(String message) {
        super(message);
    }
}