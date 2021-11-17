package ddd.lab;

import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

/**
 * @author ge
 * @date 2021/11/15
 */
@Component
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
