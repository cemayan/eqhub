package org.dark.eqhub.messagerelayer.domain.service;

import org.dark.eqhub.messagerelayer.domain.model.Outbox;
import org.dark.eqhub.messagerelayer.domain.port.input.OutboxUsecase;
import org.dark.eqhub.messagerelayer.domain.port.output.KafkaPort;
import org.dark.eqhub.messagerelayer.domain.port.output.MongoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
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
        return mongoPort.getAllOutboxEvents();
    }


    @Scheduled(fixedRate = 5000)
    @Override
    public void fireEvent() {
        logger.info("outboxEvents is retrieving from db and being sent to kafka... ");
        getAllOutboxEvents().doOnNext(x -> {
            kafkaPort.SendEvent(x).doOnNext(y -> {
                mongoPort.removeOutboxEvent(x.getId());
            }).subscribe();
        }).subscribe();
    }
}

