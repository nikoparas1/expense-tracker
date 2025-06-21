package com.expenseTracker.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDto {
    private Long id;

    @Column(name = "expense_name")
    private String expenseName;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "expense_amount")
    private BigDecimal expenseAmount;
}
