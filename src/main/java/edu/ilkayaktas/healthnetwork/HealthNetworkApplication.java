package edu.ilkayaktas.healthnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.ilkayaktas.healthnetwork")
public class HealthNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthNetworkApplication.class, args);
	}
}
