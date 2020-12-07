package com.banking.transaction.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

	
	
	public String financeTransactions(Map<String,Object> user);

}
