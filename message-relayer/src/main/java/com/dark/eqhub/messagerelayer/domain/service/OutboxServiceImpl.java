package com.dark.eqhub.messagerelayer.domain.service;

import com.dark.eqhub.messagerelayer.domain.model.Outbox;
import com.dark.eqhub.messagerelayer.domain.port.input.OutboxUsecase;
import com.dark.eqhub.messagerelayer.domain.port.output.KafkaPort;
import com.dark.eqhub.messagerelayer.domain.port.output.MongoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OutboxServiceImpl implements OutboxUsecase {

    private static final Logger logger = LoggerFactory.getLogger(OutboxServiceImpl.class);

    private final MongoPort mongoPort;
    private final KafkaPort kafkaPort;

    public OutboxServiceImpl(MongoPort mongoPort, KafkaPort kafkaPort) {
        this.mongoPort = mongoPort;
        this.kafkaPort = kafkaPort;
    }


    @Override
    public Flux<Outbox> getAllOutboxEvents() {
        return null;
    }
}

