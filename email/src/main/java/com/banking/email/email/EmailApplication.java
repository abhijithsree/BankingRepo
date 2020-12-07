package com.banking.email.email;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.banking.email.consumer.EmailConsumer;
import com.banking.email.serviceImpl.BankingNotificationServiceImple;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Sink.class)
@ComponentScan({"com.banking.email"})
public class EmailApplication {
    Logger logger = LoggerFactory.getLogger(EmailApplication.class);

	@Autowired
	EmailConsumer consumer;

	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
		}
	@StreamListener(target = Sink.INPUT)
    public void createLoginForUser(String user) throws JsonMappingException, JsonProcessingException{
		logger.info("message from user module",user);
		consumer.sendNotification(user);
    }
}
