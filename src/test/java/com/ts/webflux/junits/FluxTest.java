package com.ts.webflux.junits;

import java.util.Arrays;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

	@Test
	public void firstFluxTest() {		
		Flux<String> fluxEvents=Flux.just("A","B","C","D");
		fluxEvents.log().subscribe();
		
	}
	
	@Test
	public void firstFluxPrint() {		
		Flux<String> fluxEvents=Flux.just("A","B","C","D");
		fluxEvents.log().subscribe(System.out::println);
		
	}
	
	@Test
	public void fluxTestElements() {			
		Flux<String> fluxEvents=Flux.just("A","B","C","D");		
		StepVerifier.create(fluxEvents.log()).expectNext("A").expectNext("B").expectNext("C").expectNext("D").verifyComplete();		
		
	}
	
	@Test
	public void fluxFromIterable() {	
		Flux.fromIterable(Arrays.asList("A","B","C","D")).log().subscribe();
		
	}
	
	
	
	@Test
	public void fluxTestElements_withException() {
		
		Flux<String> fluxEvents=Flux.just("A","B","C","D").concatWith(Flux.error(new RuntimeException())).log();
		
		StepVerifier.create(fluxEvents.log()).expectNext("A").expectNext("B").expectNext("C").expectNext("D").expectError(RuntimeException.class).verify();	
		
	
	}
	
	
	
}




