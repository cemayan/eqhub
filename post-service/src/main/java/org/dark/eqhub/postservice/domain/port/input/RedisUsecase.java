package org.dark.eqhub.postservice.domain.port.input;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RedisUsecase {
    void Put(String key, String hashKey, Object data);
    Optional<String> Get(String key);
}
