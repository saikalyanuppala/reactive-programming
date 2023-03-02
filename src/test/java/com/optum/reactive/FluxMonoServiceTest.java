package com.optum.reactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FluxMonoServiceTest {

	FluxMonoService service = new FluxMonoService();

	@Test
	void testNamesFlux() {
		Flux<String> namesFlux = service.namesFlux();
		StepVerifier.create(namesFlux).expectNext("sai").expectNext("kalyan").expectNext("uppala").verifyComplete();

		StepVerifier.create(namesFlux).expectNextCount(3).verifyComplete();
		StepVerifier.create(namesFlux).expectNext("sai").expectNextCount(2).verifyComplete();
	}

	@Test
	void testNameMono() {
		Mono<String> nameMono = service.nameMono();
		StepVerifier.create(nameMono).expectNextCount(1).verifyComplete();
	}
}
