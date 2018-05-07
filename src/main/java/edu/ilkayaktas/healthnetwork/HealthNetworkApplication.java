package edu.ilkayaktas.healthnetwork;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.ilkayaktas.healthnetwork")
public class HealthNetworkApplication  implements CommandLineRunner {

	@Autowired
	Logger logger;

	public static void main(String[] args) {
		SpringApplication.run(HealthNetworkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Application started!!!");
	}
}
