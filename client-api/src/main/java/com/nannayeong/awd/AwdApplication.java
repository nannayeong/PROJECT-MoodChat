package com.nannayeong.awd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AwdApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwdApplication.class, args);
	}

	@GetMapping(value = "/")
	public ResponseEntity index() {

		return ResponseEntity.ok("Hello World");
	}
}
