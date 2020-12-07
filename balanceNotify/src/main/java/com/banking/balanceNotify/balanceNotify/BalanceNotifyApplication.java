package com.banking.balanceNotify.balanceNotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;

import com.banking.balanceNotify.consumer.EmailConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@EnableBinding(Sink.class)
@ComponentScan({"com.banking.balanceNotify"})
public class BalanceNotifyApplication {
    Logger logger = LoggerFactory.getLogger(BalanceNotifyApplication.class);

	@Autowired
	EmailConsumer consumer;
	public static void main(String[] args) {
		SpringApplication.run(BalanceNotifyApplication.class, args);
	}
	@StreamListener(target = Sink.INPUT)
	public void informUserAboutAccount(String user) throws JsonMappingException, JsonProcessingException {
		logger.info("message from bank balance service",user);
		consumer.sendNotification(user);
		
	}

}
