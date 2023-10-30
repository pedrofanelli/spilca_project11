package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"controllers","processors"})
public class SpilcaProject11Application {

	public static void main(String[] args) {
		SpringApplication.run(SpilcaProject11Application.class, args);
	}

}
