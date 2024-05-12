package com.secondhand.presentationadvertapi.infrastructure.configuration.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@Configuration
@ConfigurationProperties("advert.kafka")
public class KafkaProperties {
    private List<String> servers;
    private String applicationName;

    private KafkaProducerProperties producer;

    @Setter
    @Getter
    public static class KafkaProducerProperties {
        private Map<String, String> topics;
    }
}