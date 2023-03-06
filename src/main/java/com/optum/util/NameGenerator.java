package com.optum.util;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

public class NameGenerator {

	private List<String> cacheList = new ArrayList<>();

	public Flux<String> generateNames() {
		return Flux.generate(syncSink -> {
			System.out.println("generated fresh");
			Util.sleepSeconds(1);
			String name = Util.faker().name().firstName();
			cacheList.add(name);
			syncSink.next(name);
		}).cast(String.class).startWith(cacheNames());
	}

	public Flux<String> cacheNames() {
		return Flux.fromIterable(cacheList);
	}

	public static List<String> getListNames(int count) {
		List<String> names = new ArrayList<>();
		for (int i = 0; i < count; i++)
			names.add(getName());
		return names;
	}

	public static Flux<String> getFluxNames(int count) {
		return Flux.range(1, count).map(i -> getName());
	}

	private static String getName() {
		Util.sleepSeconds(1);
		return Util.faker().name().fullName();
	}
}
