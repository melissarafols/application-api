package com.java.assessment.service;

import com.java.assessment.dto.AccountHolderDto;
import com.java.assessment.dto.MessageResponse;

import java.math.BigDecimal;

public interface AccountHolderService {

    MessageResponse createAccountCredentials(AccountHolderDto dto);

    MessageResponse getAccountDetails(String accountPin);

    void saveAccountBalance(String accountPin, BigDecimal amount);
}
