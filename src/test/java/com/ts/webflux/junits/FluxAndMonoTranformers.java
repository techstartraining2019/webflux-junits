package com.ts.webflux.junits;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTranformers {


  List<String> names = Arrays.asList("Kiran", "Shasank", "Sanhitha", "Sowmaya", "Laxmi");


  @Test
  public void mapTest() {
    Flux<Integer> tranformElements = Flux.range(1, 5).map(i -> i * 10); // 1,2,3,4,5
    StepVerifier.create(tranformElements.log()).expectNext(10, 20, 30, 40, 50).verifyComplete();

  }

  @Test
  public void mapUpperCaseTest() {

    Flux<String> tranformElements = Flux.fromIterable(names).map(s -> s.toUpperCase());


    StepVerifier.create(tranformElements.log())
        .expectNext("KIRAN", "SHASANK", "SANHITHA", "SOWMAYA", "LAXMI").verifyComplete();
  }



  @Test
  public void flatMapTest() {

    // Flux<Integer> tranformElements = Flux.range(1, 5).flatMap(i -> Flux.range(i * 10, 3));

    Flux.range(1, 5).flatMap(i -> Flux.range(i * 10, 3)).subscribe(System.out::println);

    // StepVerifier.create(tranformElements.log()).expectNext(10, 11, 20, 21, 30, 31, 40, 41, 50,
    // 51)
    // .verifyComplete();
  }

  @Test
  public void tranformUsingFlatMap() {

    Flux<String> tranformElements = Flux.fromIterable(names).flatMap(s -> {
      return Flux.fromIterable(convertToList(s));
    });

    StepVerifier.create(tranformElements.log()).expectNextCount(10).verifyComplete();
  }

  private List<String> convertToList(String s) {

    return Arrays.asList(s, "newValue");
  }



}
