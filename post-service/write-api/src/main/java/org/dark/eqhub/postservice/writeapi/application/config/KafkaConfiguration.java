package org.dark.eqhub.postservice.writeapi.application.config;


import org.dark.eqhub.postservice.writeapi.application.constants.Constants;
import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ReceiverOptions<String, Outbox> kafkaReceiverOptions(final KafkaProperties kafkaProperties) {
        final ReceiverOptions<String, Outbox> options = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return options.subscription(Collections.singletonList(Constants.KAFKA_TOPIC_NAME));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, Outbox> reactiveKafkaConsumerTemplate(final ReceiverOptions<String, Outbox> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<String, Outbox>(kafkaReceiverOptions);
    }
}
