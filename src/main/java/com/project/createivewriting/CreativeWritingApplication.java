package com.project.createivewriting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.project.createivewriting.model.entity")
@ComponentScan(basePackages = "com.project")
public class CreativeWritingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreativeWritingApplication.class, args);
	}

}
