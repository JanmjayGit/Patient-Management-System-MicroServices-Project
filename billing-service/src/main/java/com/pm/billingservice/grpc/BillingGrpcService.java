package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    // billingRequest: Contains the data sent by the gRPC client (request)
    // responseObserver: Used by the server to send the response back to the client.
    // Here--> StreamObserver<billing.BillingResponse> means the server will send a BillingResponse back to the client.
    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
                                     StreamObserver<billing.BillingResponse> responseObserver){

        log.info("createBillingAccount request received {}", billingRequest.toString());

        // business logic e.g, save to database, perform, calculate, etc

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response); // send the data
        responseObserver.onCompleted(); // finish the communication

    }
}

/* StreamObserver is a gRPC interface that allows the server to asynchronously send responses,
notify the client when the response is complete, or report errors. */
