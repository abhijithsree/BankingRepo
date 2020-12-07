package com.banking.user.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.banking.user"})
@EntityScan({"com.banking.user"})
@EnableJpaRepositories({"com.banking.user"})
public class UserApplication {
    static Logger logger = LoggerFactory.getLogger(UserController.class);



	public static void main(String[] args) {
		logger.info("User Service Is Statting");
		SpringApplication.run(UserApplication.class, args);
	}



}
