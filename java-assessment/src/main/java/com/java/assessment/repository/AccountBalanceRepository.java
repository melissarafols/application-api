package com.java.assessment.repository;


import com.java.assessment.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {

    @Query(name = "AccountBalance.getCurrentBalance", nativeQuery = true)
    AccountBalance getCurrentBalance(@Param("accountPin") String accountPin);
}
