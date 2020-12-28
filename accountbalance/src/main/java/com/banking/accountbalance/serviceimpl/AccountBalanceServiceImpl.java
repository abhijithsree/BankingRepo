package com.banking.accountbalance.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.banking.accountbalance.model.BankingUser;
import com.banking.accountbalance.producer.AccountProducer;
import com.banking.accountbalance.repository.BankAccountRepository;
import com.banking.accountbalance.service.AccountBalanceService;
@Service
@EnableBinding(AccountProducer.class)
public class AccountBalanceServiceImpl implements AccountBalanceService{

	
	@Autowired
	BankAccountRepository bankAccount;
	
	@Autowired
	AccountProducer producer;
	
	@Override
	public BankingUser getBankAccountDetails(Map<String, Object> user) {
		System.out.println(user);
		Integer creditAmount=0;
		Integer debitAmount=0;
		Integer balance =0;
		String name=(String) user.get("name");
		String emailId=(String) user.get("emailId");
		String accountnumber=(String) user.get("accountNumber");
		String transaction ="";
		double amount =0;
		if((String) user.get("trasnaction")!=null) {
		 transaction =(String) user.get("trasnaction");
		}
		if(user.get("amount")!=null) {
		 amount =(double) user.get("amount");
		}
		 creditAmount=bankAccount.getAccountDetails(emailId, accountnumber,"C");
		 debitAmount=bankAccount.getAccountDetails(emailId, accountnumber,"D");
		 System.out.println(creditAmount);
		 System.out.println(debitAmount);
		 if(debitAmount!=null) {
		 balance = creditAmount-debitAmount;
		 }
		 else {
			 balance= creditAmount;
		 }
		BankingUser bankingUser = new BankingUser();
		if(balance!=null) {
		bankingUser.setAccountBalance(balance);
		}
		bankingUser.setAccountNumber(accountnumber);
		bankingUser.setEmailId(emailId);
		bankingUser.setName(name);
		bankingUser.setTrasnaction(transaction);
		bankingUser.setAmount(amount);
		System.out.println(bankingUser);
		producer.emailAccountNotification().send(MessageBuilder.withPayload(bankingUser).build());
		return bankingUser;
	}

}
