package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.repositories;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, String> {
    @Query(value = "select * from bank_account_api_db.bank_accounts bc where bc.account_id=:accountId", nativeQuery = true)
    BankAccountEntity findAccountById(@Param("accountId") String accountId);
    @Query(nativeQuery = true, value = "select * from bank_account_api_db.bank_accounts bc where bc.account_state='CREATED'")
    Collection<BankAccountEntity> findAllAccountsByStateCreated();
    @Query(nativeQuery = true, value = "select * from bank_account_api_db.bank_accounts bacc order by bacc.created_at desc")
    Collection<BankAccountEntity> findAllCreatedAccounts();
}
