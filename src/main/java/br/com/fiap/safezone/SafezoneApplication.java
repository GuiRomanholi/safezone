package br.com.fiap.safezone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SafezoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafezoneApplication.class, args);
	}

}
