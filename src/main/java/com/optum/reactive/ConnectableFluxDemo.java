package com.optum.reactive;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ConnectableFluxDemo {
	public static void main(String[] args) {

		var flux = Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
		ConnectableFlux<Integer> conFlux = flux.publish();
		conFlux.connect();

		conFlux.subscribe(Util.subscriber("durga"));
		Util.sleepSeconds(2);
		conFlux.subscribe(Util.subscriber("chandi"));

		Util.sleepSeconds(10);
	}
}
