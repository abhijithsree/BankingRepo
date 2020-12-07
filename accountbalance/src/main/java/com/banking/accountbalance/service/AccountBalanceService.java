package com.banking.accountbalance.service;

import java.util.Map;

import com.banking.accountbalance.model.BankingUser;

public interface AccountBalanceService {

	public BankingUser getBankAccountDetails(Map<String,Object> user);
}
