package com.optum.programatically;

import java.util.Random;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxGenerate {
	public static void main(String[] args) {
//		generate_error();

//		generate_condition();

		generate_1();
	}

	public static void generate_condition() {
		Flux.generate(() -> 1, (state, syncSink) -> {
			syncSink.next(state * 2);
			if (state == 10)
				syncSink.complete();
			return state + 1;
		}).subscribe(Util.subscriber());
	}

	public static void generate_1() {
		Flux.generate(sink -> {
			Random random = new Random();
			int randomInt = random.nextInt(20);
			sink.next(randomInt);
			if (randomInt == 10)
				sink.complete();
		}).subscribe(Util.subscriber());
	}

	public static void generate_error() {
		// flunk synchronous sink next() should be called only once
		// if more than once next() is called it will give error : More than one call to
		// onNext
		// onNext should be invoked again and again based on downstream demand
		Flux.generate(syncSink -> {
			syncSink.next(10);
			syncSink.next(20);
			syncSink.complete();
		}).subscribe(Util.subscriber());

		Flux.generate(syncSink -> {
			syncSink.next(10);
			syncSink.complete();
		}).subscribe(Util.subscriber());
	}
}
