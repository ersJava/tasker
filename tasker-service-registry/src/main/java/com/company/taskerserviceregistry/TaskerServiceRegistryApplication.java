package com.company.taskerserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaskerServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskerServiceRegistryApplication.class, args);
	}

}
