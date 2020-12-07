package com.banking.transaction.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import com.banking.transaction.entities.BankAccount;
import com.banking.transaction.model.BankingUser;
import com.banking.transaction.producer.TransactionProducer;
import com.banking.transaction.repository.BankAccountRepository;
import com.banking.transaction.service.TransactionService;

@EnableBinding(TransactionProducer.class)
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionProducer creditProducer;
	
	@Autowired
	BankAccountRepository accountRepo;
	
	@Override
	public String financeTransactions(Map<String, Object> user) {
		if("C".equals((String) user.get("transactionstatus"))) {
			BankingUser response = createAccount(user);
			if(response!=null) {
				response.setTrasnaction("Credit");
				response.setAmount((double) user.get("amount"));
				creditProducer.financeTransaction().send(MessageBuilder.withPayload(response).build());
				return "Credit Done For The Account";
			}
		}
		else if("D".equals((String) user.get("transactionstatus"))){
			BankingUser bankAccount =getAccountDetails(user);
			double amount =(double) user.get("amount");
			if(bankAccount.getAccountBalance()>amount) {
				BankingUser response = createAccount(user);
				if(response!=null) {
					response.setTrasnaction("Debit");
					response.setAmount(amount);
					creditProducer.financeTransaction().send(MessageBuilder.withPayload(response).build());
					return "Amount Debited From Account";
				}
			}
			else {
				return "Insufficiant Account Balance";

			}
		}
		return "Finance Transaction Failed";
	}
	private BankingUser createAccount(Map<String, Object> user) {
		BankAccount account = new BankAccount();
		account.setName((String) user.get("name"));
		account.setAmount((double) user.get("amount"));
		account.setEmailId((String) user.get("emailid"));
		account.setTransactionStatus((String) user.get("transactionstatus"));
		account.setAccountNumber((String) user.get("accountnumber"));
		BankAccount output=accountRepo.save(account);
		BankingUser response = new BankingUser(output);
		return response;
	}
	public BankingUser getAccountDetails(Map<String, Object> user) {
		Integer creditAmount=0;
		Integer debitAmount=0;
		Integer balance =0;
		String name=(String) user.get("name");
		String emailId=(String) user.get("emailid");
		String accountnumber=(String) user.get("accountnumber");
		 creditAmount=accountRepo.getAccountDetails(emailId, accountnumber,"C");
		 debitAmount=accountRepo.getAccountDetails(emailId, accountnumber,"D");
		 if(debitAmount!=null) {
		 balance = creditAmount-debitAmount;
		 }
		 else {
			 balance= creditAmount;
		 }
		BankingUser bankingUser = new BankingUser();
		bankingUser.setAccountBalance(balance);
		bankingUser.setAccountNumber(accountnumber);
		bankingUser.setEmailId(emailId);
		bankingUser.setName(name);
		return bankingUser;
	}
}
