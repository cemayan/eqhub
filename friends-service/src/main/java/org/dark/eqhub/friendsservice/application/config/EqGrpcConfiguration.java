package org.dark.eqhub.friendsservice.application.config;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import org.dark.eqhub.friendsservice.domain.service.EventsGrpcServiceImpl;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class EqGrpcConfiguration {



    @PostConstruct
    public  void startGrpcServer() {
        Server server = ServerBuilder.forPort(9898)
                .addService(new EventsGrpcServiceImpl()).build();

        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
