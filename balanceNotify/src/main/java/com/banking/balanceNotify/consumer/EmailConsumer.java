package com.banking.balanceNotify.consumer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.balanceNotify.service.BankingNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailConsumer {

	@Autowired
	BankingNotificationService emailService;
	public void sendNotification(String user) throws JsonMappingException, JsonProcessingException {
		 ObjectMapper objectmapper = new ObjectMapper();
	       Map<String,Object> userMap = objectmapper.readValue(user, Map.class);
	       emailService.sendNotificationToUser((String)userMap.get("emailId"), (String)userMap.get("password"),(String)userMap.get("name"),(String)userMap.get("accountNumber"),(double)userMap.get("accountBalance"),(String)userMap.get("trasnaction"),(double)userMap.get("amount"));
	}
}
