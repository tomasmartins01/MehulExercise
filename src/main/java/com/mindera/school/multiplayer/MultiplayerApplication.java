package com.mindera.school.multiplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
public class MultiplayerApplication {

	public static void main(String[] args) {
		org.springframework.context.ConfigurableApplicationContext run = run(MultiplayerApplication.class, args);
	}

}
