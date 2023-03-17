package org.dark.eqhub.friendsservice.domain.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import org.dark.eqhub.friendsservice.domain.port.input.FriendsUsecase;
import org.dark.eqhub.proto.Event;
import org.dark.eqhub.proto.ReactorEventgRPCServiceGrpc;
import org.dark.eqhub.proto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EventsGrpcServiceImpl extends ReactorEventgRPCServiceGrpc.EventgRPCServiceImplBase {

    private final FriendsUsecase friendsUsecase;
    private final ObjectMapper objectMapper;

    public EventsGrpcServiceImpl(FriendsUsecase friendsUsecase, ObjectMapper objectMapper) {
        this.friendsUsecase = friendsUsecase;
        this.objectMapper = objectMapper;
    }

    @Override
    public Flux<Response> sendEvent(Mono<Event> request) {

      return    request.flatMapMany(event-> {
         return friendsUsecase.getFriends(event.getUserName()).flatMap(x-> {


             try {
                 byte[] bytes = objectMapper.writeValueAsBytes(x);
                 return Flux.just(Response.newBuilder().setEventData(ByteString.copyFrom(bytes)).build());
             } catch (JsonProcessingException e) {
                 return Flux.just(Response.newBuilder().build());
             }
        });
        });

    }
}
