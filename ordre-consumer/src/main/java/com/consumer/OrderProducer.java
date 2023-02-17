package com.consumer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class OrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		
		KafkaProducer<String, Integer> producer =new KafkaProducer(props);
		ProducerRecord<String, Integer> record= new ProducerRecord<>("OrderTopic", "Macbook", 10);
	
		try {
		producer.send(record,new OrderCallback());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			producer.close();
		}
		
	}

}
