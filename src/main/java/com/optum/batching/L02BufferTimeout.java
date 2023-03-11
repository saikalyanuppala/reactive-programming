package com.optum.batching;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class L02BufferTimeout {
	public static void main(String[] args) {
		eventStream().bufferTimeout(5,Duration.ofSeconds(2)).subscribe(Util.subscriber());

		Util.sleepSeconds(30);
	}

	private static Flux<String> eventStream() {
		return Flux.interval(Duration.ofMillis(800)).map(i -> "event" + i);
	}
}
