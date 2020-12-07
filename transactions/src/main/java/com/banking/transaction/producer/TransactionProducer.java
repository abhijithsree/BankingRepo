package com.banking.transaction.producer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TransactionProducer {

	@Output("financeTransactionChannels")
	MessageChannel financeTransaction();
}
