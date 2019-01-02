package com.example.mongoexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private Start start;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

