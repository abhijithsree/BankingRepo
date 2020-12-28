package com.banking.accountbalance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.accountbalance.entities.BankAccount;


@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Long>{

	
	@Query(value="SELECT SUM(b.amount) FROM BANKUSERACCOUNT b WHERE b.emailid = ?1 and b.accountnumber = ?2 and b.transactionStatus=?3",nativeQuery=true)
	public Integer getAccountDetails(String emailid,String accountnumber,String transactionStatus);
	

}
