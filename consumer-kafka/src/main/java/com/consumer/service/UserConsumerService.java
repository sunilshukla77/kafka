package com.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerService {
	
	@KafkaListener(topics = {"kafka_topic"})
	public void ConsumerUserData(String message) {
		System.out.println("Message recieved : "+ message);
	}

}
