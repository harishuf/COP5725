package com.autoseconds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoSecondsApp {
	public static void main(String[] args) {
		SpringApplication.run(AutoSecondsApp.class, args);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("System shut down");
			}
		});
	}
}
