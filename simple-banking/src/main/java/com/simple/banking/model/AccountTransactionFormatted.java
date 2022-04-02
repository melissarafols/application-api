package com.simple.banking.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class AccountTransactionFormatted {
	private int id;
	private String accountPin;
	private BigDecimal debitAmount;
	private BigDecimal creditAmount;
	private BigDecimal runningBalance;
	private String dateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountPin() {
		return accountPin;
	}
	public void setAccountPin(String accountPin) {
		this.accountPin = accountPin;
	}
	public BigDecimal getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public BigDecimal getRunningBalance() {
		return runningBalance;
	}
	public void setRunningBalance(BigDecimal runningBalance) {
		this.runningBalance = runningBalance;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


}
