package com.java.assessment.service;

import com.java.assessment.dto.MessageResponse;
import com.java.assessment.dto.TransactionDto;

public interface TransactionService {
    MessageResponse processDebitAndCredit(TransactionDto dto);
}
