package com.banking.user.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

public interface UserRegistrationSource {

	@Output("userRegistrationChannels")
	MessageChannel userRegistration();
	
	@Output("userCallingAccountBalanceChannel")
	MessageChannel userCallingAccountBalance();
}

