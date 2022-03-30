package com.java.assessment.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION_HISTORY")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = TransactionHistory.class)
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="account_pin")
    private String accountPin;
    @Column(name="amount")
    private BigDecimal amount;
    @Column(name="running_balance")
    private BigDecimal runningBalance;
    @Column(name="transaction_type")
    private String transactionType;
    @Column(name="datetime")
    private Timestamp dateTime;
}
