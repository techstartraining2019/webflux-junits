package com.ts.webflux.junits;

import java.time.Duration;
import org.junit.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ColdAndHotPublisherTest {

  @Test
  public void coldPublisherTest() throws InterruptedException {
    Flux<String> stringFlux =
        Flux.just("A", "B", "C", "D", "E", "F").delayElements(Duration.ofSeconds(1)); // created
                                                                                      // publisher


    stringFlux.subscribe(s -> System.out.println("Subscriber1:" + s)); // added subscriber 1

    Thread.sleep(2000); // added time delay

    stringFlux.subscribe(s -> System.out.println("Subscriber2:" + s)); // added subscriber2

    Thread.sleep(4000);
  }

  @Test
  public void hotPublisherTest() throws InterruptedException {

    Flux<String> stringFlux =
        Flux.just("A", "B", "C", "D", "E", "F").delayElements(Duration.ofSeconds(1));

    ConnectableFlux<String> connectableFlux = stringFlux.publish();
    connectableFlux.connect();

    connectableFlux.subscribe(s -> System.out.println("Subscriber1:" + s)); // added subscriber 1

    Thread.sleep(2000); // added time delay

    connectableFlux.subscribe(s -> System.out.println("Subscriber2:" + s)); // added subscriber2

    Thread.sleep(4000);

  }

}
