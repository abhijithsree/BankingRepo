package com.banking.authenticate.consumer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.banking.authenticate.entities.LoginUser;
import com.banking.authenticate.model.BankingUser;
import com.banking.authenticate.repository.LoginRepository;
import com.banking.authenticate.service.AuthService;
import com.banking.authenticate.util.AuthUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthenticateConsumer {

	@Autowired
	LoginRepository loginrepo;

	@Autowired
	AuthService authService;
	
	    public void createLoginUser(String User) throws JsonMappingException, JsonProcessingException {
	        System.out.println("uSER "+User);
	        ObjectMapper objectmapper = new ObjectMapper();
	       Map<String,Object> userMap = objectmapper.readValue(User, Map.class);
	       BankingUser finaluser =authService.createLogin(userMap);

	    }
}
