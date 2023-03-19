package org.dark.eqhub.postservice.domain.service;


import org.dark.eqhub.postservice.domain.port.input.RedisUsecase;
import org.dark.eqhub.postservice.domain.port.output.RedisPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisServiceImpl implements RedisUsecase {

    private final RedisPort redisPort;

    public RedisServiceImpl(RedisPort redisPort) {
        this.redisPort = redisPort;
    }

    @Override
    public void Put(String key, String hashKey, Object data) {
        redisPort.Put(key,hashKey, data);
    }

    @Override
    public Optional<String> Get(String key) {
        return Optional.empty();
    }
}
