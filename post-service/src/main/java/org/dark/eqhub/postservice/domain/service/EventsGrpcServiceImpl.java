package org.dark.eqhub.postservice.domain.service;


import org.dark.eqhub.common.Constants;
import org.dark.eqhub.common.util.Utils;
import org.dark.eqhub.postservice.domain.port.input.EventGrpcUsecase;
import org.dark.eqhub.proto.Event;
import org.dark.eqhub.proto.ReactorEventgRPCServiceGrpc;
import org.dark.eqhub.proto.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EventsGrpcServiceImpl implements EventGrpcUsecase {

    private final ReactorEventgRPCServiceGrpc.ReactorEventgRPCServiceStub reactorEventgRPCServiceStub;


    public EventsGrpcServiceImpl(ReactorEventgRPCServiceGrpc.ReactorEventgRPCServiceStub reactorEventgRPCServiceStub) {
        this.reactorEventgRPCServiceStub = reactorEventgRPCServiceStub;

    }

    public Flux<Response> sendFriendListEvent() {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Mono<Event> eventMono = Mono.just(
                Event
                        .newBuilder()
                        .setAggregateId(Utils.GetUUID())
                        .setEventDate(Utils.GetDate())
                        .setEventName(Constants.GET_FRIENDS_LIST)
                        .setUserName(principal.getUsername())
                        .build());
        return reactorEventgRPCServiceStub.sendEvent(eventMono);
    }


}
