package com.banking.accountbalance.accountbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.banking.accountbalance.consumer.AccountConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@EnableBinding(Sink.class)
@EnableJpaRepositories({"com.banking.accountbalance"})
@EntityScan({"com.banking.accountbalance"})
@ComponentScan({"com.banking.accountbalance"})
@EnableEurekaClient
public class AccountbalanceApplication {

	@Autowired
	AccountConsumer accountConsumer;
	
	public static void main(String[] args) {
		SpringApplication.run(AccountbalanceApplication.class, args);
	}

	@StreamListener(target = Sink.INPUT)
	public void sendAccountBalance(String user) throws JsonMappingException, JsonProcessingException {
		accountConsumer.accountBalanceEvent(user);
	}
}
