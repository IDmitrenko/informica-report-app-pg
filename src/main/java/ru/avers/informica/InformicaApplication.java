package ru.avers.informica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InformicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InformicaApplication.class, args);
	}

}
