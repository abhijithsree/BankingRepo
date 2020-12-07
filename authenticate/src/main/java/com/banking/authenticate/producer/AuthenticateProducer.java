package com.banking.authenticate.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AuthenticateProducer {

	@Output("userPasswordGivingChannel")
	MessageChannel passwordUpdationChannel();
	
	
}
