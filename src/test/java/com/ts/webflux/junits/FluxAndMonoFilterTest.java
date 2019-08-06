package com.ts.webflux.junits;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoFilterTest {

  List<String> names = Arrays.asList("Kiran", "Shasank", "Sanhitha", "Sowmaya", "Laxmi");

  @Test
  public void filterTest() {
    Flux<String> namesFlux = Flux.fromIterable(names).filter(s -> s.endsWith("a")).log();


    StepVerifier.create(namesFlux).expectNext("Sanhitha").expectNext("Sowmaya").verifyComplete();

  }

  @Test
  public void filterLengthTest() {

    Flux<String> namesFlux = Flux.fromIterable(names).filter(s -> s.length() <= 5).log();

    StepVerifier.create(namesFlux).expectNext("Kiran", "Laxmi").verifyComplete();

  }

}
