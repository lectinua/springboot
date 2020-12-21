package com.example.demogradle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemogradleApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemogradleApplication.class, args);

		EventBus sync = new EventBus("sync");

		ExecutorService executor = Executors.newFixedThreadPool(5);
		AsyncEventBus async = new AsyncEventBus("async", executor);

		async.register(new EventBusExampleListener());
		sync.register(new EventBusExampleListener());

		async.post("ASYNC event");
		sync.post("SYNC event");
		async.post("BLOCKING ASYNC event");

		System.out.println(">>> END");

		if(!executor.awaitTermination(10, TimeUnit.SECONDS)) {
			executor.shutdown();
		}
	}

	public static class EventBusExampleListener {

		@Subscribe
		public void receive(String message) throws InterruptedException {
			receiving(3, message);
		}

		private static void receiving(int count, String message) throws InterruptedException {
			for (int i = 0; i < count; i++) {
				System.out.println(">>> " + message);
				TimeUnit.SECONDS.sleep(1L);
			}
		}
	}
}
