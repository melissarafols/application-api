package com.java.assessment.controller;

import com.java.assessment.dto.AccountHolderDto;
import com.java.assessment.dto.MessageResponse;
import com.java.assessment.model.AccountHolder;
import com.java.assessment.service.AccountHolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class AccountHolderController {

    @Autowired
    private AccountHolderService accountHolderService;

    public AccountHolderController(
            AccountHolderService accountHolderService) {
        this.accountHolderService = accountHolderService;
    }

    @PostMapping(path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewAccount(
            @RequestBody AccountHolderDto dto) {
        try {
            log.info("==== Start: Save New Account Holder ====");
            MessageResponse response = accountHolderService.createAccountCredentials(dto);
            log.info("==== End: Save New Account Holder ====");
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

    @GetMapping(path = "/account/{accountPin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAccountDetails(@PathVariable("accountPin") String accountPin) {
        try {
            log.info("==== Start: Get Current Balance of Account Holder ====");
            MessageResponse response = accountHolderService.getAccountDetails(accountPin);
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
