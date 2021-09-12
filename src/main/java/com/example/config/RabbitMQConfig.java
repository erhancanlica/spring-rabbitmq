package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.queue}")
    private String queue;

    @Value("${rabbit.exchange}")
    private String exchange;

    @Value("${rabbit.routingkey}")
    private String key;

    @Bean
    Queue queue() {
        return new Queue(queue);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding(final Queue queue, final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(key);
    }
}
