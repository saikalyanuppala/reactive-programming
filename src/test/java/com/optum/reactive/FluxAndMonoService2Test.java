package com.optum.reactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoService2Test {

	@Test
	void namesFluxTest() {
		Flux<String> namesFlux = FluxAndMonoService2.namesFlux();
		
		StepVerifier.create(namesFlux)
		            .expectNextCount(4)
		            .verifyComplete();
		
		StepVerifier.create(namesFlux)
		            .expectNext("sai")
		            .expectNext("narasimha")
		            .expectNext("durga")
		            .expectNext("narayana")
		            .verifyComplete();
		
		
	   StepVerifier.create(namesFlux)
			        .expectNext("sai")
			        .expectNext("narasimha")
			        .expectNextCount(2)
			        .verifyComplete();
	}
}
