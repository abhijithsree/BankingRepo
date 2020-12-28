package com.banking.transaction.tx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.banking.transaction"})
@EnableJpaRepositories({"com.banking.transaction"})
@EntityScan({"com.banking.transaction"})
public class TransactionApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}
	
	
}
