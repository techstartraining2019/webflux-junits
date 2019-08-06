package com.ts.webflux.junits;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoBackPressure {

  @Test
  public void backPressureTest() {
    Flux<Integer> fluxElements = Flux.range(1, 10);

    StepVerifier.create(fluxElements.log()).expectSubscription().thenRequest(1).expectNext(1)
        .thenRequest(1).expectNext(2).thenCancel().verify();
  }


  @Test
  public void backPressureTest1() {

    Flux<Integer> fluxElements = Flux.range(1, 10);

    fluxElements.subscribe(element -> System.out.println("Element is:" + element),
        (e) -> System.err.println("Exception is:" + e), () -> System.out.println("done"),
        (subscription -> subscription.request(4)));

  }

}
