package com.revature.EnergySocialNetwork;

import com.revature.EnergySocialNetwork.services.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class EnergySocialNetworkApplication implements CommandLineRunner {
	@Resource
	FilesStorageService filesStorageService;

	public static void main(String[] args) {
		SpringApplication.run(EnergySocialNetworkApplication.class, args);
	}

	public void run(String... arg) throws Exception {
		filesStorageService.deleteAll();
		filesStorageService.init();
	}

}
