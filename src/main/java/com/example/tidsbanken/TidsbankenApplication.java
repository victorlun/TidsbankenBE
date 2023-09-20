package com.example.tidsbanken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.tidsbanken")
public class TidsbankenApplication {
	public static void main(String[] args) {
		SpringApplication.run(TidsbankenApplication.class, args);
	}

}
