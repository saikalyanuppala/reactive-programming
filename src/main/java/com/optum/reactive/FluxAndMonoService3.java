package com.optum.reactive;

import java.util.List;
import java.util.function.Function;

import reactor.core.publisher.Flux;

public class FluxAndMonoService3 {
	public static void main(String[] args) {
		namesFlux_defaultIfEmpty().subscribe(System.out::println);
		System.out.println("*************");
		namesFlux_switchIfEmpty().subscribe(System.out::println);
	}

	public static Flux<String> namesFlux_defaultIfEmpty() {
		Function<Flux<String>, Flux<String>> filterMap = name -> name.filter(e -> e.length() > 9)
				.map(String::toUpperCase);
		return Flux.fromIterable(List.of("sai", "narasimha", "durga", "narayana")).transform(filterMap)
				.defaultIfEmpty("default Value");
	}

	public static Flux<String> namesFlux_switchIfEmpty() {
		Flux<String> defualtFlux = Flux.just("default");
		Function<Flux<String>, Flux<String>> filterMap = name -> name.filter(e -> e.length() > 9)
				.map(String::toUpperCase);
		return Flux.fromIterable(List.of("sai", "narasimha", "durga", "narayana")).transform(filterMap)
				.switchIfEmpty(defualtFlux);
	}
}
