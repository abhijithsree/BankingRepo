package com.banking.accountbalance.model;

import com.banking.accountbalance.entities.BankAccount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankingUser {

	public BankingUser() {
		super();
	}

	public BankingUser(BankAccount response) {
		super();
		this.name = response.getName();
		this.emailId = response.getEmailId();
		this.accountNumber = response.getAccountNumber();
	}

	
	public String name;
	
	public String emailId;
	
	public String accountNumber;
	
	public double accountBalance;
	
	public String trasnaction;
	
	public double amount;

	public String getName() {
		return name;
	}

	public String getTrasnaction() {
		return trasnaction;
	}

	public void setTrasnaction(String trasnaction) {
		this.trasnaction = trasnaction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "BankingUser [name=" + name + ", emailId=" + emailId + ", accountNumber=" + accountNumber
				+ ", accountBalance=" + accountBalance + "]";
	}
}
