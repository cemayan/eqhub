package org.dark.eqhub.postservice.writeapi.domain.port.input;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface RedisUsecase {
    void Put(String hashKey, Object data);
    Mono<Object> Get(String key);
}
