package com.banking.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.transaction.entities.BankAccount;
import com.banking.transaction.entities.BankTransaction;


@Repository
public interface BankTransactionRepository extends CrudRepository<BankTransaction,Long>{

	

	
}
