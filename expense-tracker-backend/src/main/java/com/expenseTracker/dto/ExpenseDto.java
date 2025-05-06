package com.expenseTracker.dto;

import java.math.BigDecimal;

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

    private String expenseName;

    private String expenseCategory;

    private BigDecimal expenseAmount;
}
