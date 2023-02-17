package com.sun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerKafka {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	String kafkaTopic= "kafka_topic";
	
	public void sendData(String message) {
		kafkaTemplate.send(kafkaTopic,message);
	}

}
