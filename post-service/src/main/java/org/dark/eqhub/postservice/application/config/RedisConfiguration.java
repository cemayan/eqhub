package org.dark.eqhub.postservice.application.config;

import org.dark.eqhub.postservice.domain.model.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    ReactiveRedisOperations<String, Post> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Post> serializer = new Jackson2JsonRedisSerializer<>(Post.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Post> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());


        RedisSerializationContext<String, Post> context = builder
                .key(new StringRedisSerializer())
                .hashKey(new StringRedisSerializer())
                .hashValue(serializer)
                .value(serializer)
                .build();



        return new ReactiveRedisTemplate<>(factory, context);
    }
}
