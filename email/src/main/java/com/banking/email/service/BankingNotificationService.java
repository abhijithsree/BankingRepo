package com.banking.email.service;

public interface BankingNotificationService {

	String sendNotificationToUser(String emailId,String password,String name);
}
