package com.ts.webflux.junits;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoErrorTest {

  @Test
  public void fluxErrorHandling_onErrorResume() {

    Flux<String> fluxString = Flux.just("A", "B", "C", "D")
        .concatWith(Flux.error(new RuntimeException("Exception Occured")))
        .concatWith(Flux.just("E")).onErrorResume(e -> {
          System.out.println("Exception is :" + e);
          return Flux.just("default", "response");
        });
    StepVerifier.create(fluxString.log()).expectSubscription().expectNext("A", "B", "C", "D")
        .expectNext("default", "response").verifyComplete();

  }

  @Test
  public void fluxErrorHandling_onErrorReturn() {

    Flux<String> fluxString = Flux.just("A", "B", "C", "D")
        .concatWith(Flux.error(new RuntimeException("Exception Occured")))
        .concatWith(Flux.just("E")).onErrorReturn("default value");

    StepVerifier.create(fluxString.log()).expectSubscription().expectNext("A", "B", "C", "D")
        .expectNext("default value").verifyComplete();
  }

  @Test
  public void fluxErrorHandling_onErrorMap() {

    Flux<String> fluxString = Flux.just("A", "B", "C", "D")
        .concatWith(Flux.error(new RuntimeException("Exception Occured")))
        .concatWith(Flux.just("E")).onErrorMap(e -> new CustomException(e));

    StepVerifier.create(fluxString.log()).expectSubscription().expectNext("A", "B", "C", "D")
        .expectError(CustomException.class).verify();
  }

}
