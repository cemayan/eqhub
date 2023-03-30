package org.dark.eqhub.messagerelayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableReactiveMongoRepositories
@ComponentScan({"org.dark.eqhub.*"})
@EnableScheduling
public class MessageRelayerApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(MessageRelayerApplication.class, args);
    }

}
