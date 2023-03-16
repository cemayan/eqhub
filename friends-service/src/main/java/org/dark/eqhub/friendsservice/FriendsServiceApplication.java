package org.dark.eqhub.friendsservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableReactiveNeo4jRepositories;

@SpringBootApplication
@EnableReactiveNeo4jRepositories
@ComponentScan({"org.dark.eqhub.*"})
public class FriendsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendsServiceApplication.class, args);
    }

}
