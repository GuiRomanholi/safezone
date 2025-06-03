package br.com.fiap.safezone;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


//http://localhost:8081/swagger-ui/index.html
@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info =
@Info(title = "API SafeZone", description = "API RESTful com Swagger para SafeZone", version = "v1"))
public class SafezoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafezoneApplication.class, args);
	}

}
