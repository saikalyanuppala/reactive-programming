package com.optum.coldhotpublishers;

import java.time.Duration;
import java.util.stream.Stream;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class HotPublisher_AutoConnect {
	private static Stream<String> getMovie() {
		System.out.println("Got movie streaming request");
		return Stream.of("scene 1", "scene 2", "scene 3", "scene 4", "scene 5", "scene 6", "scene 7", "scene 8");
	}

	public static void main(String[] args) {

		// auto connect like live cricket match. there is no re subscription
		// observe means autoConnect(0) and autoConnect(1) results
		Flux<String> fluxMovie = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(1)).publish()
				.autoConnect(1); 
//				.autoConnect(0); // no subscriber is needed to emit

		Util.sleepSeconds(3);
		fluxMovie.subscribe(Util.subscriber("sam"));

		Util.sleepSeconds(10);
		System.out.println("sam about to join ");
		fluxMovie.subscribe(Util.subscriber("mike"));

		Util.sleepSeconds(50);
	}
}
