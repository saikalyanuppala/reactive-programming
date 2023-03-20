package com.optum.programatically;

import java.util.List;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxHandle {
	public static void main(String[] args) {

		// handle --> combination of generate + map + filter
		Flux.fromIterable(List.of(1, 2, 3, 4, 5))
		    .handle((ele, syncSink) -> {
			   if (ele >= 3)
				 syncSink.next(ele * 2);
		}).subscribe(Util.subscriber());
	}
}
