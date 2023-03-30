package org.dark.eqhub.messagerelayer.domain.port.input;


import org.dark.eqhub.messagerelayer.domain.model.Outbox;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface OutboxUsecase {
    Flux<Outbox> getAllOutboxEvents();

    void fireEvent();
}
