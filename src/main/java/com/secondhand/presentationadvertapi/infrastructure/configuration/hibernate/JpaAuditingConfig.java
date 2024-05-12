package com.secondhand.presentationadvertapi.infrastructure.configuration.hibernate;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditor")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> auditor() {
        return () -> Optional.of(MDC.getCopyOfContextMap().getOrDefault("X-UserEmail", "ANONYMOUS_USER"));
    }
}

