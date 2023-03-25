package com.dark.eqhub.messagerelayer.domain.port.input;


import com.dark.eqhub.messagerelayer.domain.model.Outbox;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface OutboxUsecase {
    Flux<Outbox> getAllOutboxEvents();
}
