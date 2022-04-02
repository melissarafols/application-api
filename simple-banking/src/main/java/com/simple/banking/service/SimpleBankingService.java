package com.simple.banking.service;

import com.simple.banking.model.AccountDebitCreditTransaction;
import com.simple.banking.model.AccountHolder;
import com.simple.banking.model.AccountTransactionFormatted;
import com.simple.banking.model.MessageResponse;
import com.simple.banking.model.AccountTransactionHistory;

import java.util.List;

public interface SimpleBankingService {

	AccountHolder saveAccount(AccountHolder holder);

	AccountHolder getAccountDetails(String accountPin);

	AccountHolder saveAccountTransaction(AccountDebitCreditTransaction debitCreditTransaction);

	List<AccountTransactionFormatted> getTransactionHistory(String accountPin);

}
