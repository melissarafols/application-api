package com.java.assessment.controller;

import com.java.assessment.dto.AccountHolderDto;
import com.java.assessment.dto.MessageResponse;
import com.java.assessment.dto.TransactionDto;
import com.java.assessment.model.TransactionHistory;
import com.java.assessment.service.AccountHolderService;
import com.java.assessment.service.TransactionHistoryService;
import com.java.assessment.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionHistoryService transactionHistoryService;

    public TransactionController(TransactionService transactionService,
                                 TransactionHistoryService transactionHistoryService) {
        this.transactionService = transactionService;
        this.transactionHistoryService = transactionHistoryService;
    }

    @PostMapping(path = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewAccount(
            @RequestBody TransactionDto dto) {
        try {
            log.info("==== Start: Save " + dto.getTransactionType() + "Transaction of Account Holder ====");
            MessageResponse response = transactionService.processDebitAndCredit(dto);
            log.info("==== End: Save " + dto.getTransactionType() + "Transaction of Account Holder ====");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageResponse msgResponse = new MessageResponse(400, "fail",
                    "Error was encountered while processing your request", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(msgResponse);
        }
    }

    @GetMapping(path = "/transaction-history/{accountPin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAccountDetails(@PathVariable("accountPin") String accountPin) {
        try {
            log.info("==== Start: Get Current Balance of Account Holder ====");
            List<TransactionHistory> response = transactionHistoryService.getAllTransactionRecords(accountPin);
            log.info("==== End: Get Current Balance of Account Holder ====");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageResponse msgResponse = new MessageResponse(400, "fail",
                    "Error was encountered while processing your request", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(msgResponse);
        }
    }
}
