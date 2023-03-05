package com.optum.coldhotpublishers;

import java.time.Duration;
import java.util.stream.Stream;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class HotPublishCache {
	private static Stream<String> getMovie() {
		System.out.println("Got movie streaming request");
		return Stream.of("scene 1", "scene 2", "scene 3", "scene 4", "scene 5", "scene 6", "scene 7", "scene 8");
	}

	public static void main(String[] args) {
//cache the emitted data for late subscribers
// cache = publish() + replay()
		Flux<String> fluxMovie = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(1)).cache();
//cache(2) stores all 2 events
//cache() stores Integer.MAX_VALUE events		
		Util.sleepSeconds(2);
		fluxMovie.subscribe(Util.subscriber("sam"));

		Util.sleepSeconds(10);
		System.out.println("sam about to join ");
		fluxMovie.subscribe(Util.subscriber("mike"));

		Util.sleepSeconds(50);
	}
}
