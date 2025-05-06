package com.expenseTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/")
@NoArgsConstructor
public class RootController {
    @GetMapping("/")
    public String root() {
        return "Welcome to ExpenseTracker API";
    }

}
