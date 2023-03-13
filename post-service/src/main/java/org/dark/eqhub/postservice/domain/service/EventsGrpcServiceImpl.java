package org.dark.eqhub.postservice.domain.service;


import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dark.eqhub.proto.Event;
import org.dark.eqhub.proto.EventgRPCServiceGrpc;
import org.springframework.stereotype.Service;


@Service
public class EventsGrpcServiceImpl {

    @GrpcClient("local-grpc-server")
    private  EventgRPCServiceGrpc.EventgRPCServiceBlockingStub blockingStub;

    public String receiveGreeting(String name) {
        Event request = Event.newBuilder()
                .setAggregateId(name)
                .build();
        return blockingStub.sendEvent(request).getAggregateId();
    }

}
