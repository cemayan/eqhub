package com.dark.eqhub.messagerelayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@ComponentScan({"org.dark.eqhub.*"})
public class MessageRelayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageRelayerApplication.class, args);
    }

}
