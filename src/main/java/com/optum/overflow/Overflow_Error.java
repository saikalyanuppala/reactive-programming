package com.optum.overflow;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Overflow_Error {
	public static void main(String[] args) {
		
		// check this condition fluxSink.isCancelled() before emitting values.
	       System.setProperty("reactor.bufferSize.small", "16");
			Flux.create(fluxSink -> {
				for (int i = 1; i <= 200 && !fluxSink.isCancelled(); i++) {
					fluxSink.next(i);
					System.out.println("Pushed " + i);
					Util.sleepMillis(1);
				}
				fluxSink.complete();
			})
			.onBackpressureError() //added this call
			.publishOn(Schedulers.boundedElastic())
			.doOnNext(i -> Util.sleepMillis(10))
			.subscribe(Util.subscriber());
			
			Util.sleepSeconds(10);
		}
}
