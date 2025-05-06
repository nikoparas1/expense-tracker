package com.expenseTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseTrackerController {

    @GetMapping("/")
    @ResponseBody
    public String expenseTracker() {
        return "Tracking Expenses...";
    }
}
