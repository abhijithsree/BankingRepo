package com.banking.accountbalance.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AccountProducer {

	@Output("emailAccountNotificationChannel")
	MessageChannel emailAccountNotification();
}
