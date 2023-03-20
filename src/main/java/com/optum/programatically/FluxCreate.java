package com.optum.programatically;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxCreate {

	public static void main(String[] args) {
		Flux.create(fluxSink -> {
			fluxSink.next("sai");
			fluxSink.next("sandra");
			fluxSink.next("durga");
			
			fluxSink.complete();
		})
		.subscribe(Util.subscriber());
	}
}
