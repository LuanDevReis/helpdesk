package com.corecode.helpdesk.services;

import com.corecode.helpdesk.domain.dtos.ChamadoCriadoEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChamadoEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ChamadoEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public ChamadoEventPublisher(
            RabbitTemplate rabbitTemplate,
            @Value("${app.rabbitmq.exchange}") String exchange,
            @Value("${app.rabbitmq.routing-key}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void publicarChamadoCriado(ChamadoCriadoEventDTO event) {
        rabbitTemplate.convertAndSend(exchange, routingKey, event);

        logger.info(
                "Evento CHAMADO_CRIADO publicado. chamadoId={}, clienteEmail={}",
                event.getChamadoId(),
                event.getClienteEmail()
        );
    }
}
