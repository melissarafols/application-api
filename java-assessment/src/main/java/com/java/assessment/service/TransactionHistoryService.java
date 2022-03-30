package com.java.assessment.service;

import com.java.assessment.model.TransactionHistory;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionHistoryService {
    List<TransactionHistory> getAllTransactionRecords(String accountPin);
    void saveTransactionHistory(String accountPin, String transactionType, BigDecimal creditBalance, BigDecimal remainingBalance);
}
