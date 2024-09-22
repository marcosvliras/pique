package com.pique.pique;

import com.pique.pique.domain.entities.UserEntity;
import com.pique.pique.domain.entities.enums.UserType;
import com.pique.pique.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiqueApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PiqueApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	UserEntity user = new UserEntity(
	// 			"John",
	// 			"Doe",
	// 			"12345678902",
	// 			"teste2@gmail.com",
	// 			"123456",
	// 			UserType.PF
	// 	);
	// 	System.out.println(user);
	// 	userRepository.save(user);
	// }
}
