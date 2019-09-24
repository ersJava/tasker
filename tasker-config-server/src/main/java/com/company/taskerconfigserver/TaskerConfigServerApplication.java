package com.company.taskerconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TaskerConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskerConfigServerApplication.class, args);
	}

}
