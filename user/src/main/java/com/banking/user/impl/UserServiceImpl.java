package com.banking.user.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;

import com.banking.user.entities.BankAccount;
import com.banking.user.model.BankingUser;
import com.banking.user.producer.UserRegistrationSource;
import com.banking.user.repository.BankAccountRepository;
import com.banking.user.service.UserService;
import com.banking.user.user.UserController;

@Service
@EnableBinding(UserRegistrationSource.class)
public class UserServiceImpl implements UserService{
    Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	BankAccountRepository accountRepo;

	
	@Autowired
	UserRegistrationSource  userSource;

	@Override
	public BankingUser createUser(Map<String, Object> user) {
        Random rand = new Random(); 
		BankAccount account = new BankAccount();
		account.setName((String) user.get("name"));
		account.setAmount((double) user.get("amount"));
		account.setEmailId((String) user.get("emailid"));
		account.setTransactionStatus((String) user.get("transactionstatus"));
		account.setAccountNumber(String.valueOf(rand.nextInt(100000)));
		BankAccount output=accountRepo.save(account);
		BankingUser response = new BankingUser(output);

		if(output!=null) {
			userSource.userRegistration().send(MessageBuilder.withPayload(output).build());
			logger.info("Send to User Registration Q", output);
		}
		return response;
	}

	
}
