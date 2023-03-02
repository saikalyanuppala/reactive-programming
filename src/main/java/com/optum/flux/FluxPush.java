package com.optum.flux;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxPush {
	public static void main(String[] args) {
		// push is not thread safe
		Flux.push(fluxSink -> {
			fluxSink.next(10);
			fluxSink.next(20);
			fluxSink.complete();
		}).subscribe(Util.subscriber());
	}
}
