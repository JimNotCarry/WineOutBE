package com.WineOutBE;

import com.WineOutBE.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WineOutBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineOutBeApplication.class, args);
	}

	//@Bean //Uncomment to add users
	CommandLineRunner run(UserService userService) {
		return args -> {

//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_USER"));
//
//
//			userService.saveUser(new User(null,"khberg", "pass123", new ArrayList<>()));
//			userService.saveUser(new User(null, "bellabus", "123pass", new ArrayList<>()));
//			userService.saveUser(new User(null, "lindz0r", "123pass", new ArrayList<>()));
			//userService.saveUser(new User(null, "lhberg", "123pass", new ArrayList<>()));


//			userService.addRoleToUser("khberg", "ROLE_USER");
//			userService.addRoleToUser("bellabus", "ROLE_USER");
//			userService.addRoleToUser("lindz0r", "ROLE_USER");
			//userService.addRoleToUser("lhberg", "ROLE_ADMIN");
		};
	};

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

}
