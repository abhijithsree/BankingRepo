package com.banking.balanceNotify.service;

public interface BankingNotificationService {

	String sendNotificationToUser(String emailId,String password,String name,String accountNumber,double balance,String transaction, double amount);
}
