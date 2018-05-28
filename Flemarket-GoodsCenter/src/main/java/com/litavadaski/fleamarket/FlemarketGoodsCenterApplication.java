package com.litavadaski.fleamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class FlemarketGoodsCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlemarketGoodsCenterApplication.class, args);
	}
}
