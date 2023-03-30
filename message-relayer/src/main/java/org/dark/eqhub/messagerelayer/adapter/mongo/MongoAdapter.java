package org.dark.eqhub.messagerelayer.adapter.mongo;


import org.dark.eqhub.messagerelayer.adapter.kafka.KafkaAdapter;
import org.dark.eqhub.messagerelayer.domain.model.Outbox;
import org.dark.eqhub.messagerelayer.domain.port.output.MongoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class MongoAdapter implements MongoPort {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAdapter.class);

    private final OutboxRepository outboxRepository;

    public MongoAdapter(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @Override
    public void removeOutboxEvent(String id) {
        outboxRepository.deleteById(id).subscribe();
    }

    @Override
    public Flux<Outbox> getAllOutboxEvents() {
        return outboxRepository.findAll();
    }
}
