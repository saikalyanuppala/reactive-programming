package com.optum.coldhotpublishers;

import java.time.Duration;
import java.util.stream.Stream;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class HotPublisher_RefCount {
	private static Stream<String> getMovie() {
		System.out.println("Got movie streaming request");
		return Stream.of("scene 1", "scene 2", "scene 3", "scene 4", "scene 5", "scene 6", "scene 7", "scene 8");
	}

	public static void main(String[] args) {
		// share = publish().refCount(1)
		// refCount(2) -- means if minimum 2 subscribers are there, flux emits the data.
		Flux<String> fluxMovie = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(2)).publish()
				.refCount(1);
		        //.refCount(2);

		fluxMovie.subscribe(Util.subscriber("sam"));

		Util.sleepSeconds(5);

		fluxMovie.subscribe(Util.subscriber("mike"));

		Util.sleepSeconds(50);
	}
}
