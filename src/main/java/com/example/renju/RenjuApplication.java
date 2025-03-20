package com.example.renju;

import com.example.renju.ui.ConsoleInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RenjuApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RenjuApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsoleInterface.main(args);
	}
}
