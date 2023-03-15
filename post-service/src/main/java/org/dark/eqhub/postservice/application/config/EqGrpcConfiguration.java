package org.dark.eqhub.postservice.application.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.dark.eqhub.proto.EventgRPCServiceGrpc;
import org.dark.eqhub.proto.ReactorEventgRPCServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EqGrpcConfiguration {


    @Bean
    public EventgRPCServiceGrpc.EventgRPCServiceBlockingStub eventBlockingStub() {
        return  EventgRPCServiceGrpc.newBlockingStub(getChannel());
    }

    @Bean
    @Scope("singleton")
    public ManagedChannel getChannel(){

        return ManagedChannelBuilder.forAddress("localhost", 9898)
                .usePlaintext()
                .build();
    }

    @Bean
    @Scope("singleton")
    public EventgRPCServiceGrpc.EventgRPCServiceBlockingStub getBlockingStub(ManagedChannel managedChannel){
        return EventgRPCServiceGrpc.newBlockingStub(managedChannel);
    }



    @Bean
    @Scope("singleton")
    public ReactorEventgRPCServiceGrpc.ReactorEventgRPCServiceStub getReactiveBlockingStub(ManagedChannel managedChannel){
        return ReactorEventgRPCServiceGrpc.newReactorStub(managedChannel);
    }


}
