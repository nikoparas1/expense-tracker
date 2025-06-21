package com.expenseTracker.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank
    private String expenseName;

    @NotBlank
    private String expenseCategory;

    @NotNull
    @PositiveOrZero
    private BigDecimal expenseAmount;
}
