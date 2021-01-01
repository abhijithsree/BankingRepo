package com.banking.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.transaction.entities.BankAccount;


@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Long>{

	

	@Query(value="SELECT SUM(b.amount) FROM BANKUSERTRANSACTION b WHERE  b.accountnumber = ?1 and b.transactionStatus=?2",nativeQuery=true)
	public Integer getAccountDetails(String accountnumber,String transactionStatus);
	

	

}
