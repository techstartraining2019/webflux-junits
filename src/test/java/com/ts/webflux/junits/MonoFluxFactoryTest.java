package com.ts.webflux.junits;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class MonoFluxFactoryTest {


  List<String> names = Arrays.asList("Kiran", "Shasank", "Sanhitha", "Sowmaya", "Laxmi");

  @Test
  public void fluxUsingIterable() {
    Flux<String> namesFlux = Flux.fromIterable(names).log();
    StepVerifier.create(namesFlux).expectNext("Kiran", "Shasank", "Sanhitha", "Sowmaya", "Laxmi")
        .verifyComplete();
  }

  @Test
  public void fluxUsingStream() {
    Flux<String> namesFlux = Flux.fromStream(names.stream()).log();

    StepVerifier.create(namesFlux).expectNext("Kiran", "Shasank", "Sanhitha", "Sowmaya", "Laxmi")
        .verifyComplete();
  }

  @Test
  public void fluxFromRange() {

    Flux<Integer> intergerFlux = Flux.range(1, 6);

    StepVerifier.create(intergerFlux.log()).expectNext(1, 2, 3, 4, 5, 6).verifyComplete();

  }

  @Test
  public void fluxFromInterval() {
    Flux<Long> longInterval = Flux.interval(Duration.ofSeconds(1));
    StepVerifier.create(longInterval.log().take(2)).expectNext(0l).expectNext(1l).verifyComplete();

  }

  @Test
  public void monoUsingSupplier() {
    Supplier<String> stringSupplier = () -> "admin";

    Mono<String> stringMono = Mono.fromSupplier(stringSupplier);

    StepVerifier.create(stringMono.log()).expectNext("admin").verifyComplete();
  }

  @Test
  public void monoUsingJustOrEmpty() {

    Mono<String> monoNull = Mono.justOrEmpty(null); // Mono has empty value

    StepVerifier.create(monoNull.log()).verifyComplete();

  }

}
