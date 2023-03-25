package org.dark.eqhub.postservice.writeapi.adapter.redis;


import org.dark.eqhub.postservice.writeapi.domain.port.output.FeedsRedisPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FeedsRedisAdapter implements FeedsRedisPort {

    private static final Logger logger = LoggerFactory.getLogger(FeedsRedisAdapter.class);
    private final FeedsRedisRepository feedsRedisRepository;

    public FeedsRedisAdapter(FeedsRedisRepository feedsRedisRepository) {
        this.feedsRedisRepository = feedsRedisRepository;
    }

    @Override
    public void put(String hashKey, String data) {
        feedsRedisRepository.addToList(hashKey, data).subscribe();
    }
}
