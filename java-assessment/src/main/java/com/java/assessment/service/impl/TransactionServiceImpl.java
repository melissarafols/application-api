package com.java.assessment.service.impl;

import com.java.assessment.dto.AccountHolderDto;
import com.java.assessment.dto.MessageResponse;
import com.java.assessment.dto.TransactionDto;
import com.java.assessment.model.AccountBalance;
import com.java.assessment.repository.AccountBalanceRepository;
import com.java.assessment.repository.AccountHolderRepository;
import com.java.assessment.service.AccountHolderService;
import com.java.assessment.service.TransactionHistoryService;
import com.java.assessment.service.TransactionService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    AccountBalanceRepository accountBalanceRepository;
    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    TransactionHistoryService transactionHistoryService;


    @Override
    public MessageResponse processDebitAndCredit(TransactionDto dto) {

        if (StringUtils.isNotBlank(dto.getTransactionType()) &&
                ("CREDIT".equalsIgnoreCase(dto.getTransactionType()) ||
                        "DEBIT".equalsIgnoreCase(dto.getTransactionType()))) {
            return processTransaction(dto);
        }
        MessageResponse response = new MessageResponse();
        response.setCode(400);
        response.setStatus("Failed");
        response.setMessage("Invalid Request");
        response.setResult(dto);
        return response;
    }

    private MessageResponse processTransaction(TransactionDto dto) {
        AccountBalance dbBalance = accountBalanceRepository.getCurrentBalance(dto.getAccountPin());
        if ("CREDIT".equalsIgnoreCase(dto.getTransactionType())) {
            BigDecimal newBalance = dbBalance.getRemainingBalance().add(dto.getAmount());
            accountHolderService.saveAccountBalance(dto.getAccountPin(), newBalance);
            transactionHistoryService.saveTransactionHistory(dto.getAccountPin(), dto.getTransactionType(), dto.getAmount(), newBalance);
        } else if ("DEBIT".equalsIgnoreCase(dto.getTransactionType())) {
            BigDecimal newBalance = dbBalance.getRemainingBalance().subtract(dto.getAmount());
            accountHolderService.saveAccountBalance(dto.getAccountPin(), newBalance);
            transactionHistoryService.saveTransactionHistory(dto.getAccountPin(), dto.getTransactionType(), dto.getAmount(), newBalance);
        }
        MessageResponse checkBalance = accountHolderService.getAccountDetails(dto.getAccountPin());
        return checkBalance;
    }
}
