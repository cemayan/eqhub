package org.dark.eqhub.postservice.writeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableCaching
@ComponentScan({"org.dark.eqhub.*"})
public class WriteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WriteApiApplication.class, args);
    }

}
