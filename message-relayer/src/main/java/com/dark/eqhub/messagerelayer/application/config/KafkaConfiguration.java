package com.dark.eqhub.messagerelayer.application.config;


import com.dark.eqhub.messagerelayer.domain.model.Outbox;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ReactiveKafkaProducerTemplate<String, Outbox> reactiveKafkaProducerTemplate(final KafkaProperties properties) {
        final Map<String, Object> props = properties.buildProducerProperties();
        return new ReactiveKafkaProducerTemplate<String, Outbox>(SenderOptions.create(props));
    }
}
