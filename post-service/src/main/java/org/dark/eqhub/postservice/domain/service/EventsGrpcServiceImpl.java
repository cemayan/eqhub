package org.dark.eqhub.postservice.domain.service;


import org.dark.eqhub.proto.Event;
import org.dark.eqhub.proto.ReactorEventgRPCServiceGrpc;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EventsGrpcServiceImpl {

    private final ReactorEventgRPCServiceGrpc.ReactorEventgRPCServiceStub reactorEventgRPCServiceStub;

    public EventsGrpcServiceImpl(ReactorEventgRPCServiceGrpc.ReactorEventgRPCServiceStub reactorEventgRPCServiceStub) {
        this.reactorEventgRPCServiceStub = reactorEventgRPCServiceStub;
    }

    public void sendEvent() {
        Flux<Event> eventFlux = reactorEventgRPCServiceStub.sendEvent(Mono.just(Event.newBuilder().setAggregateId("test").build()));
        eventFlux.parallel().subscribe();
    }


}
