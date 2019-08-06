package com.ts.webflux.junits;

import org.junit.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {
	
	@Test
	public void monoTest() {		
		Mono<String> stringMono=Mono.just("Spring");		
		StepVerifier.create(stringMono.log()).expectNext("Spring").verifyComplete();		
	}
	
	@Test
	public void monoTest_Error() {		
		StepVerifier.create(Mono.error(new RuntimeException("Exception Occured")).log()).expectError(RuntimeException.class).verify();	
		
	}

}
