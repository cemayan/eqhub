package org.dark.eqhub.postservice.writeapi.adapter.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.dark.eqhub.postservice.writeapi.domain.port.output.KafkaPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class KafkaAdapter implements KafkaPort {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAdapter.class);

    private final ReactiveKafkaConsumerTemplate<String, Outbox> kafkaConsumerTemplate;

    public KafkaAdapter(ReactiveKafkaConsumerTemplate<String, Outbox> kafkaConsumerTemplate) {
        this.kafkaConsumerTemplate = kafkaConsumerTemplate;
    }

    @Override
    public Flux<Outbox> consumeEvents() {
        return kafkaConsumerTemplate
                .receive()
                .map(ConsumerRecord::value);
    }
}
