package com.optum.batching;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.javafaker.Book;
import com.optum.util.Util;

import lombok.Getter;
import lombok.ToString;
import reactor.core.publisher.Flux;

public class L04Assignment {
	public static void main(String[] args) {

		Set<String> allowedCategories = Set.of("Science fiction", "Fantasy", "Suspense/Thriller");

		bookStream()
		.filter(b -> allowedCategories.contains(b.getCategoty()))
		.buffer(Duration.ofSeconds(5))
		.map(list -> revenueCalculator(list))
		.subscribe(Util.subscriber());
		
		Util.sleepSeconds(60);
	}

	private static Flux<BooKorder> bookStream() {
		return Flux.interval(Duration.ofMillis(300)).map(i -> new BooKorder());
	}

	private static RevenueReport revenueCalculator(List<BooKorder> books) {
		Map<String, Double> revenue = books.stream()
				.collect(Collectors.groupingBy(BooKorder::getCategoty, Collectors.summingDouble(BooKorder::getPrice)));
		return new RevenueReport(revenue);
	}
}

@Getter
@ToString
class BooKorder {
	private String title;
	private String author;
	private double price;
	private String categoty;

	public BooKorder() {
		Book book = Util.faker().book();
		this.title = book.title();
		this.author = book.author();
		this.categoty = book.genre();
		this.price = Double.parseDouble(Util.faker().commerce().price());
	}
}

@ToString
class RevenueReport {
	private LocalDateTime localDateTime = LocalDateTime.now();
	private Map<String, Double> revenue;

	public RevenueReport(Map<String, Double> revenue) {
		super();
		this.revenue = revenue;
	}
}