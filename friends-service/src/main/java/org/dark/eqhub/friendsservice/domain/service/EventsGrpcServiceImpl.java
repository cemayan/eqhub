package org.dark.eqhub.friendsservice.domain.service;


import org.dark.eqhub.proto.Event;
import org.dark.eqhub.proto.ReactorEventgRPCServiceGrpc;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EventsGrpcServiceImpl extends ReactorEventgRPCServiceGrpc.EventgRPCServiceImplBase {

    @Override
    public Flux<Event> sendEvent(Mono<Event> request) {
            return request.flux().doOnNext(System.out::println);
    }
}
