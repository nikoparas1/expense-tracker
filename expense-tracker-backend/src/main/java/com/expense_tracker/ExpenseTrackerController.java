package com.expense_tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExpenseTrackerController {

    @GetMapping("/")
    @ResponseBody
    public String expenseTracker() {
        return "Tracking Expenses";
    }
}
