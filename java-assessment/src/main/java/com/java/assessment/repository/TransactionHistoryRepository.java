package com.java.assessment.repository;


import com.java.assessment.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository  extends JpaRepository<TransactionHistory, Long> {

    @Query(name = "TransactionHistory.getTransactionHistory", nativeQuery = true)
    List<TransactionHistory> getTransactionHistory(@Param("accountPin") String accountPin);
}
