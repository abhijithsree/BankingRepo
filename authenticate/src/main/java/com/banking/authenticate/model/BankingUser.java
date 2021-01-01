package com.banking.authenticate.model;

import com.banking.authenticate.entities.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankingUser {

	public BankingUser(LoginUser user) {
		super();
		this.name = user.getName();
		this.emailId = user.getEmailid();
		this.password = user.getPassword();
	}

	public String name;
	
	public String emailId;
	
	public String password;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAccountNumber() {
		return password;
	}

	public void setAccountNumber(String accountNumber) {
		this.password = accountNumber;
	}

	
}
