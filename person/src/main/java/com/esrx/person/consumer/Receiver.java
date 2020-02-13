package com.esrx.person.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	
	final static Logger logger = LogManager.getLogger(Receiver.class);
	
	private CountDownLatch latch = new CountDownLatch(1);
	 
	 
	public CountDownLatch getLatch() {
		return latch;
	}
	 
	@KafkaListener(topics = "${kafka.topic.boot}")
	public void receive(ConsumerRecord<?, ?> consumerRecord) {
		logger.info("recieved payload " + consumerRecord.toString());
		latch.countDown();
	}
}
