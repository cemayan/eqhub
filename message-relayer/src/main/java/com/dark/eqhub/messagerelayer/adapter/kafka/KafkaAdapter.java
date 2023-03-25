package com.dark.eqhub.messagerelayer.adapter.kafka;


import com.dark.eqhub.messagerelayer.domain.model.Outbox;
import com.dark.eqhub.messagerelayer.domain.port.output.KafkaPort;
import org.dark.eqhub.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Component
public class KafkaAdapter implements KafkaPort {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAdapter.class);

    private final ReactiveKafkaProducerTemplate<String, Outbox> kafkaProducerTemplate;

    public KafkaAdapter(ReactiveKafkaProducerTemplate<String, Outbox> kafkaProducerTemplate) {
        this.kafkaProducerTemplate = kafkaProducerTemplate;
    }

    @Override
    public Mono<SenderResult<Void>> SendEvent(Outbox outbox) {
        return kafkaProducerTemplate.send(Constants.KAFKA_TOPIC_NAME, outbox);
    }
}
