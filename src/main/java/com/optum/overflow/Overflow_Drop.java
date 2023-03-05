package com.optum.overflow;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Overflow_Drop {
	public static void main(String[] args) {
		//observe output by commenting System.setProperty
		//75% of 16 is 12
	       System.setProperty("reactor.bufferSize.small", "16");
			Flux.create(fluxSink -> {
				for (int i = 1; i <= 500; i++) {
					fluxSink.next(i);
					System.out.println("Pushed " + i);
					Util.sleepMillis(1);
				}
				fluxSink.complete();
			})
			.onBackpressureDrop() //added this call
			.publishOn(Schedulers.boundedElastic())
			.doOnNext(i -> Util.sleepMillis(10))
			.subscribe(Util.subscriber());
			
			Util.sleepSeconds(30);
		}
}
