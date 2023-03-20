package org.dark.eqhub.postservice.domain.port.input;

import org.dark.eqhub.proto.Response;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface EventGrpcUsecase {
    Flux<Response> sendFriendListEvent();
}
