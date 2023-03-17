package org.dark.eqhub.postservice.domain.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.dark.eqhub.common.Constants;
import org.dark.eqhub.common.util.Utils;
import org.dark.eqhub.proto.Event;
import org.dark.eqhub.proto.ReactorEventgRPCServiceGrpc;
import org.dark.eqhub.proto.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EventsGrpcServiceImpl {

    private final ReactorEventgRPCServiceGrpc.ReactorEventgRPCServiceStub reactorEventgRPCServiceStub;
    private final ObjectMapper objectMapper;

    public EventsGrpcServiceImpl(ReactorEventgRPCServiceGrpc.ReactorEventgRPCServiceStub reactorEventgRPCServiceStub, ObjectMapper objectMapper) {
        this.reactorEventgRPCServiceStub = reactorEventgRPCServiceStub;
        this.objectMapper = objectMapper;
    }

    public void sendEvent() {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Mono<Event> eventMono = Mono.just(
                Event
                        .newBuilder()
                        .setAggregateId(Utils.GetUUID())
                        .setEventDate(Utils.GetDate())
                        .setEventName(Constants.GET_FRIENDS_LIST)
                        .setUserName(principal.getUsername())
                        .build());
        Flux<Response> eventFlux = reactorEventgRPCServiceStub.sendEvent(eventMono);
        eventFlux.parallel().subscribe();
    }


}
