package com.optum.flux;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxTake {
	public static void main(String[] args) {
		Flux.range(1, 10).take(3).subscribe(Util.subscriber());
	}
}
