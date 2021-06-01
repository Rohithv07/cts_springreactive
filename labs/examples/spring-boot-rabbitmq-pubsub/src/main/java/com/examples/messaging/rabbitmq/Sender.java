package com.examples.messaging.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {

	// Step 1: Get reference of RabbitTemplate
	@Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
    	// Step 1: Send Message
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "demo.greeting.create", "Hello from RabbitMQ!");
    }

}