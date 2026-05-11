package com.alouane.ayoub.exam;

import com.alouane.ayoub.exam.entities.AppRole;
import com.alouane.ayoub.exam.entities.AppUser;
import com.alouane.ayoub.exam.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class AlouaneAyoubExamJeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlouaneAyoubExamJeeApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
			accountService.addNewRole(new AppRole("USER"));
			accountService.addNewRole(new AppRole("ADMIN"));

			accountService.addNewUser(new AppUser(null, "user", "1234", new ArrayList<>()));
			accountService.addNewUser(new AppUser(null, "admin", "1234", new ArrayList<>()));

			accountService.addRoleToUser("user", "USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");
		};
	}

}
