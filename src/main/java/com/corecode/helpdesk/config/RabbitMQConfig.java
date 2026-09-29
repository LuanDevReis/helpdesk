package com.corecode.helpdesk.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public DirectExchange helpdeskExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue chamadoCriadoQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Binding chamadoCriadoBinding() {
        return BindingBuilder
                .bind(chamadoCriadoQueue())
                .to(helpdeskExchange())
                .with(routingKey);
    }
}
