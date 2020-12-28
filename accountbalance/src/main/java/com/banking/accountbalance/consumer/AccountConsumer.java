package com.banking.accountbalance.consumer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.accountbalance.service.AccountBalanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class AccountConsumer {

	@Autowired
	AccountBalanceService accountService;
	
	public void accountBalanceEvent(String user) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectmapper = new ObjectMapper();
	       Map<String,Object> userMap = objectmapper.readValue(user, Map.class);
		accountService.getBankAccountDetails(userMap);
	}
}
