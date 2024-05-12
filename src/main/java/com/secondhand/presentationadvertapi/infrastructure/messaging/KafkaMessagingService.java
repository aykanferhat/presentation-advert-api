package com.secondhand.presentationadvertapi.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secondhand.presentationadvertapi.domain.events.DomainEvent;
import com.secondhand.presentationadvertapi.infrastructure.configuration.kafka.KafkaProperties;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagingService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    private final ObjectMapper objectMapper;

    public KafkaMessagingService(KafkaTemplate<String, Object> kafkaTemplate, KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(DomainEvent event, Map<String, String> headers) {
        var topic = kafkaProperties.getProducer().getTopics().get(event.getAggregateType().getName());
        var payload = getPayload(event);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, event.getAggregateId().toString(), payload);
        headers.forEach((key, value) -> producerRecord.headers().add(new RecordHeader(key, value.getBytes(StandardCharsets.UTF_8))));
        kafkaTemplate.send(producerRecord);
    }

    private String getPayload(DomainEvent event) {
        String eventPayload;
        try {
            eventPayload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error occurred while serializing event payload", e);
        }
        return eventPayload;
    }
}
