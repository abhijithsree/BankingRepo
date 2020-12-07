package com.banking.user.service;

import java.util.Map;

import com.banking.user.model.BankingUser;


public interface UserService {

	
	public BankingUser createUser(Map<String,Object> user);
	
}
