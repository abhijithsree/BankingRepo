package com.banking.authenticate.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.banking.authenticate.consumer.AuthenticateConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Sink.class)
@ComponentScan({"com.banking.authenticate"})
@EntityScan({"com.banking.authenticate"})
@EnableJpaRepositories({"com.banking.authenticate"})
public class AuthenticateApplication {

	@Autowired
	AuthenticateConsumer authenticateConsumer;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticateApplication.class, args);
	}
	 @StreamListener(target = Sink.INPUT)
	    public void createLoginForUser(String user) throws JsonMappingException, JsonProcessingException{
	        System.out.println("user"+user);
	        authenticateConsumer.createLoginUser(user);
	    }
}
