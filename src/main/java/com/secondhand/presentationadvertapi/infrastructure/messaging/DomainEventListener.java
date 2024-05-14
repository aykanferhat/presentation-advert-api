package com.secondhand.presentationadvertapi.infrastructure.messaging;

import com.secondhand.presentationadvertapi.domain.events.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DomainEventListener {

    private final KafkaMessagingService kafkaMessagingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainEventListener.class);

    public DomainEventListener(KafkaMessagingService kafkaMessagingService) {
        this.kafkaMessagingService = kafkaMessagingService;
    }

    @EventListener
    public void listen(DomainEvent event) {
        Map<String, String> threadHeaders = MDC.getCopyOfContextMap();
        kafkaMessagingService.sendMessage(event, threadHeaders);
        LOGGER.info("Domain event published. id: {}, type: {}", event.getAggregateId(), event.getAggregateType().getName());
    }
}
