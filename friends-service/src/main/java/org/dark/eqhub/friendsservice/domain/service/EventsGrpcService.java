package org.dark.eqhub.friendsservice.domain.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dark.eqhub.proto.Event;
import org.dark.eqhub.proto.EventgRPCServiceGrpc;


@GrpcService
public class EventsGrpcService extends EventgRPCServiceGrpc.EventgRPCServiceImplBase {
    @Override
    public void sendEvent(Event request, StreamObserver<Event> responseObserver) {
        Event reply = Event.newBuilder().setAggregateType("Hello ==> " + request.getAggregateId()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}