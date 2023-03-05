package com.optum.coldhotpublishers;

import java.time.Duration;
import java.util.stream.Stream;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class HotPublisher_RefCount2 {
	private static Stream<String> getMovie() {
		System.out.println("Got movie streaming request");
		return Stream.of("scene 1", "scene 2", "scene 3", "scene 4", "scene 5", "scene 6", "scene 7", "scene 8");
	}

	public static void main(String[] args) {
		// share = publish().refCount(1)
		// Observe output it behaves like a cold stream , because if second subscriber
		// joins while emitting first subscriber then it will behave like hot publisher.
		
		Flux<String> fluxMovie = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(1)).publish()
				.refCount(1);

		fluxMovie.subscribe(Util.subscriber("sam"));

		Util.sleepSeconds(10);

		fluxMovie.subscribe(Util.subscriber("mike"));

		Util.sleepSeconds(50);
	}
}