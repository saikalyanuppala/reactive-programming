package com.optum.flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class FileReaderServcei {
	private Callable<BufferedReader> openReader(Path path) {
		return () -> Files.newBufferedReader(path);
	}

	private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
		return (br, sink) -> {
			String line;
			try {
				line = br.readLine();
				if (Objects.isNull(line))
					sink.complete();
				else
					sink.next(line);
			} catch (IOException e) {
				sink.error(e);
			}
			return br;
		};

	}

	private Consumer<BufferedReader> closeReader() {
		return br -> {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}

	public Flux<String> read(Path path) {
		return Flux.generate(openReader(path), read(), closeReader());
	}

	public static void main(String[] args) {
		FileReaderServcei service = new FileReaderServcei();
		service.read(Paths.get("src/main/resources/files/file5.txt")).subscribe(Util.subscriber());
	}
}
