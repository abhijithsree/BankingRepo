package com.banking.authenticate.service;

import java.util.Map;

import com.banking.authenticate.model.BankingUser;

public interface AuthService {

	public BankingUser createLogin(Map<String,Object> loginuser);
	
	public boolean validateUser(Map<String,Object> user);
}
