package com.optum.operators;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class LimiRate {
	public static void main(String[] args) {
		System.out.println("-- request is 100, after 75% complete request(75) ");
		Flux.range(1, 1000).log().limitRate(100).subscribe(Util.subscriber());

		System.out.println("-- request is 100, after 99% complete request(99) ");
		Flux.range(1, 1000).log().limitRate(100, 99).subscribe(Util.subscriber());

		// when both are 100 and 100 -- it takes 75%
		// when both are diferent 100 and 99 -- it takes 99
	}
}
