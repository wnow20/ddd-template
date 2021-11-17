/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ddd.lab;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Component;

/**
 * A simple client that requests a greeting from the {@link GrpcServer}.
 */
@Component
public class HelloWorldClient {
  private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

  private final GreeterGrpc.GreeterBlockingStub blockingStub;

  /** Construct client for accessing HelloWorld server using the existing channel. */
  public HelloWorldClient() {
    // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
    // shut it down.

    String target = "localhost:50051";
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
        .usePlaintext()
        .build();
    // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
    blockingStub = GreeterGrpc.newBlockingStub(channel);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        System.out.println("Client await termination ERROR");
        e.printStackTrace();
      }
    }));
  }

  /** Say hello to server.
   * @return*/
  public HelloReply greet(String name) {
    logger.info("Will try to greet " + name + " ...");
    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
    HelloReply response;
    try {
      response = blockingStub.sayHello(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return null;
    }
    logger.info("Greeting: " + response.getMessage());
    return response;
  }
}
