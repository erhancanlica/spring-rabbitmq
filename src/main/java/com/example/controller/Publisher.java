package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final Binding binding;

    public Publisher(RabbitTemplate rabbitTemplate, Binding binding) {
        this.rabbitTemplate = rabbitTemplate;
        this.binding = binding;
    }

    @GetMapping("{/message}")
    public ResponseEntity<String> sendToQueue(@PathVariable("message") String message) {
        LOGGER.info("Sending message to the queue.");
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message);
        LOGGER.info("Message sent successfully to the queue");
        return new ResponseEntity<>("message sent successfully to the queue", HttpStatus.OK);
    }
}
