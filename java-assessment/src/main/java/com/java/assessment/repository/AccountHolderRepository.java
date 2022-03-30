package com.java.assessment.repository;


import com.java.assessment.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    @Query(name = "AccountHolder.getAccountDetails", nativeQuery = true)
    AccountHolder getAccountDetails(@Param("accountPin") String accountPin);
}
