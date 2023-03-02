package com.optum.reactive;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoService {

	public Flux<String> namesFlux() {
		return Flux.fromIterable(List.of("sai", "kalyan", "uppala")).log();
	}

	public Mono<String> nameMono() {
		return Mono.just("sandrananda");
	}

	public Flux<String> namesFlux_filter() {
		return Flux.fromIterable(List.of("sai", "kalyan", "uppala")).filter(e -> e.length() > 3);
	}

	public Flux<String> namesFlux_map() {
		return Flux.fromIterable(List.of("sai", "kalyan", "uppala")).map(e -> e + "-" + e.length());
	}

	public Mono<String> nameMono_map() {
		return Mono.just("sandrananda").map(String::toUpperCase);
	}
	
	public Flux<String> namesFlux_flatmap() {
		return Flux.fromIterable(List.of("sai", "kalyan", "uppala")).filter(e -> e.length()> 3)
				.flatMap(e -> splitName(e)).log();
	}
	
	public Flux<String> namesFlux_concatmap() {
		return Flux.fromIterable(List.of("sai", "kalyan", "uppala")).filter(e -> e.length()> 3)
				.concatMap(e -> splitName(e));
	}
	
	public Flux<String> splitName(String name){
		return Flux.fromArray(name.split(""));
	}

	public static void main(String[] args) {
		FluxMonoService service = new FluxMonoService();
		service.namesFlux().subscribe(System.out::println);
		service.nameMono().subscribe(System.out::println);
		service.namesFlux_filter().subscribe(System.out::println);
		service.namesFlux_map().subscribe(System.out::println);
		service.nameMono_map().subscribe(System.out::println);
		service.namesFlux_flatmap().subscribe(System.out::print);
		System.out.println();
		service.namesFlux_concatmap().subscribe(System.out::print);
	}
}
