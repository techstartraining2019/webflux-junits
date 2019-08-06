package com.ts.webflux.junits;

import java.time.Duration;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoCombineTest {


  @Test
  public void combinePlublishers() {
    Flux<String> flux1 = Flux.just("A", "B", "C");
    Flux<String> flux2 = Flux.just("D", "E", "F");

    Flux<String> mergeFlux = Flux.merge(flux1, flux2);

    StepVerifier.create(mergeFlux.log()).expectSubscription()
        .expectNext("A", "B", "C", "D", "E", "F").verifyComplete();

  }

  @Test
  public void combineUsing_withDelay() {

    Flux<String> flux1 = Flux.just("A", "B", "C").delayElements(Duration.ofSeconds(1));
    Flux<String> flux2 = Flux.just("D", "E", "F").delayElements(Duration.ofSeconds(1));

    Flux<String> mergeFlux = Flux.merge(flux1, flux2);

    StepVerifier.create(mergeFlux.log()).expectSubscription().expectNextCount(6).verifyComplete();

  }

  @Test
  public void combineUsingConcat() {

    Flux<String> flux1 = Flux.just("A", "B", "C");
    Flux<String> flux2 = Flux.just("D", "E", "F");

    Flux<String> mergeFlux = Flux.concat(flux1, flux2);

    StepVerifier.create(mergeFlux.log()).expectSubscription()
        .expectNext("A", "B", "C", "D", "E", "F").verifyComplete();

  }

  @Test
  public void combineUsingConcat_withDelay() {

    Flux<String> flux1 = Flux.just("A", "B", "C").delayElements(Duration.ofSeconds(1));
    Flux<String> flux2 = Flux.just("D", "E", "F").delayElements(Duration.ofSeconds(1));

    Flux<String> mergeFlux = Flux.concat(flux1, flux2);

    StepVerifier.create(mergeFlux.log()).expectSubscription().expectNextCount(6).verifyComplete();

  }

  @Test
  public void combineUsingZip() {

    Flux<String> flux1 = Flux.just("A", "B", "C");
    Flux<String> flux2 = Flux.just("D", "E", "F");

    // (A,D),(B,E),(C,F)

    Flux<String> mergeFlux = Flux.zip(flux1, flux2, (t1, t2) -> {
      return t1.concat(t2);

    });

    StepVerifier.create(mergeFlux.log()).expectSubscription().expectNext("AD", "BE", "CF")
        .verifyComplete();

  }

}
