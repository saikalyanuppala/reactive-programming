package com.optum.flux;

import java.util.List;

import com.optum.util.NameGenerator;
import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxVsList {
	public static void main(String[] args) {

		List<String> list = NameGenerator.getListNames(5);
		System.out.println(list);

		Flux<String> flux = NameGenerator.getFluxNames(5);
		flux.subscribe(Util.onNext());
	}
}
