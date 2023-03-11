package com.optum.batching;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class L03OverlapAndDrop {
	public static void main(String[] args) {
		eventStream().buffer(3, 1).subscribe(Util.subscriber());
//		eventStream().buffer(3,5).subscribe(Util.subscriber());

		Util.sleepSeconds(30);
	}

	private static Flux<String> eventStream() {
		return Flux.interval(Duration.ofMillis(300)).map(i -> "event" + i);
	}
}
