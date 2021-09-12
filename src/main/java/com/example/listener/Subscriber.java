package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {
    private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);

    private final Queue queue;

    public Subscriber(Queue queue) {
        this.queue = queue;
    }

    @RabbitListener(queues = "#{queue.getName()}")
    public void handleMessage(final String message) {
        LOGGER.info("Listening messages from the queue!!");
        LOGGER.info("Received the following message from the queue= " + message);
        LOGGER.info("Message received successfully from the queue.");
    }

}
