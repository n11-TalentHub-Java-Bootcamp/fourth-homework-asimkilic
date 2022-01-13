package com.asimkilic.n11.fourthhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class FourthHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FourthHomeworkApplication.class, args);
	}

	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}

}
