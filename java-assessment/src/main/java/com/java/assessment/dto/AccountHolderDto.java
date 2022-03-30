package com.java.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDto {
    private String accountPin;
    private String firstName;
    private String middleName;
    private String lastName;
    private BigDecimal accountBalance;
}
