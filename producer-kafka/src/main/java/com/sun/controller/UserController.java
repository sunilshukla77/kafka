package com.sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sun.service.ProducerKafka;
@RestController
@RequestMapping("/userapi")
public class UserController {
	  
	@Autowired
	private ProducerKafka producerKafka;

	@PostMapping("/publicerUserData/{message}")
	public void sendUserData(@PathVariable("message") String message) {
		producerKafka.sendData(message); 
	}
}
