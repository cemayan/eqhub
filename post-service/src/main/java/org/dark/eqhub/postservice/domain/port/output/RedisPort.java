package org.dark.eqhub.postservice.domain.port.output;

import org.dark.eqhub.postservice.domain.model.Post;
import reactor.core.publisher.Mono;

public interface RedisPort {
    void put(String key, String hashKey, Object data);
    Mono<Post> get(String key, String hashKey);
}
