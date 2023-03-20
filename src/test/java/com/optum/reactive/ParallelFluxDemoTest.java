package com.optum.reactive;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.optum.parallelism.ParallelFluxDemo;

import reactor.test.StepVerifier;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ParallelFluxDemoTest {
	
	@Order(1)
	@Test
	void explore_parallelTest() {
		var flux = ParallelFluxDemo.explore_parallel();
		StepVerifier.create(flux).expectNextCount(3);
	}

	@Order(2)
	@Test
	void explore_parallel_1Test() {
		var flux = ParallelFluxDemo.explore_parallel_1();
		StepVerifier.create(flux).expectNextCount(3);
	}
}
