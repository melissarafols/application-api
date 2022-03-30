package com.java.assessment.service.impl;

import com.java.assessment.dto.AccountHolderDto;
import com.java.assessment.dto.MessageResponse;
import com.java.assessment.model.AccountBalance;
import com.java.assessment.model.AccountHolder;
import com.java.assessment.repository.AccountBalanceRepository;
import com.java.assessment.repository.AccountHolderRepository;
import com.java.assessment.service.AccountHolderService;
import com.java.assessment.service.TransactionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@Slf4j
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountBalanceRepository accountBalanceRepository;
    @Autowired
    TransactionHistoryService transactionHistoryService;

    @Override
    public MessageResponse createAccountCredentials(AccountHolderDto dto) {
        AccountHolder holder = new AccountHolder();
        String accountPin = generatePin();
        holder.setAccountPin(accountPin);
        holder.setFirstName(dto.getFirstName());
        holder.setLastName(dto.getLastName());
        holder.setMiddleName(dto.getMiddleName());
        accountHolderRepository.save(holder);

        saveAccountBalance(accountPin, dto.getAccountBalance());
        transactionHistoryService.saveTransactionHistory(accountPin, "CREDIT", dto.getAccountBalance(), dto.getAccountBalance());

        MessageResponse response = new MessageResponse();
        response.setCode(200);
        response.setStatus("Success");
        response.setMessage("Successfully created new account holder");
        response.setResult(holder);
        return response;

    }

    @Override
    public MessageResponse getAccountDetails(String accountPin) {
        AccountHolder holder = accountHolderRepository.getAccountDetails(accountPin);
        AccountBalance balance = accountBalanceRepository.getCurrentBalance(accountPin);

        AccountHolderDto dto = new AccountHolderDto();
        dto.setAccountPin(accountPin);
        dto.setFirstName(holder.getFirstName());
        dto.setMiddleName(holder.getMiddleName());
        dto.setLastName(holder.getLastName());
        dto.setAccountBalance(balance.getRemainingBalance());

        MessageResponse response = new MessageResponse();
        response.setCode(200);
        response.setStatus("Success");
        response.setMessage("Successfully retrieved account balance");
        response.setResult(dto);
        return response;
    }

    private String generatePin() {
        String size = Integer.toString(Math.toIntExact(accountHolderRepository.count()) + 1);
        String accountPin = "BNK";
        int zeroAppends = 9 - size.length();
        while (accountPin.length() < zeroAppends) {
            accountPin = accountPin + "0";
        }
        accountPin = accountPin + size;
        return accountPin;
    }

    @Override
    public void saveAccountBalance(String accountPin, BigDecimal amount) {
        AccountBalance balance = accountBalanceRepository.getCurrentBalance(accountPin);
        if(balance != null){
            balance.setRemainingBalance(amount);
        }else{
            balance = new AccountBalance();
            balance.setRemainingBalance(amount);
            balance.setAccountPin(accountPin);
        }

        accountBalanceRepository.save(balance);
    }
}
