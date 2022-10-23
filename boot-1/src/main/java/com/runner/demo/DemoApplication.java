package com.runner.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);


	@Override
	public void run(String... args) throws Exception {
		logger.info("Say hi");
		System.out.println("Hello world!");
	}

	public static void main(String[] args) {
		logger.info("Start app");
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		System.out.println(applicationContext.toString());
		logger.info("Finish app");
	}
}
