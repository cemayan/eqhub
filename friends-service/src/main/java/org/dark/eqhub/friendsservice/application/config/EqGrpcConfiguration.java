package org.dark.eqhub.friendsservice.application.config;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import org.dark.eqhub.friendsservice.domain.service.EventsGrpcServiceImpl;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class EqGrpcConfiguration {

    private final EventsGrpcServiceImpl eventsGrpcService;

    public EqGrpcConfiguration(EventsGrpcServiceImpl eventsGrpcService) {
        this.eventsGrpcService = eventsGrpcService;
    }


    @PostConstruct
    public  void startGrpcServer() {
        Server server = ServerBuilder.forPort(9898)
                .addService(eventsGrpcService).build();

        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
