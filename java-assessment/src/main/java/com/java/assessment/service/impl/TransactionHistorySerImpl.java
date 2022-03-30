package com.java.assessment.service.impl;

import com.java.assessment.model.TransactionHistory;
import com.java.assessment.repository.TransactionHistoryRepository;
import com.java.assessment.service.TransactionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
@Service
@Transactional
@Slf4j
public class TransactionHistorySerImpl implements TransactionHistoryService {
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<TransactionHistory> getAllTransactionRecords(String accountPin) {
        return transactionHistoryRepository.getTransactionHistory(accountPin);
    }

    @Override
    public void saveTransactionHistory(String accountPin, String transactionType, BigDecimal creditBalance, BigDecimal remainingBalance) {
        TransactionHistory history = new TransactionHistory();
        history.setAccountPin(accountPin);
        history.setAmount(creditBalance);
        history.setTransactionType(transactionType);
        history.setRunningBalance(remainingBalance);
        history.setDateTime(new Timestamp(System.currentTimeMillis()));
        transactionHistoryRepository.save(history);
    }
}
