package com.jpa.test;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jpa.test.dao.UserRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.jpa.test.entities.User;


@SpringBootApplication
@EnableJpaRepositories("com.jpa.test.dao")
@EntityScan("com.jpa.test.entities")
@ComponentScan("com.jpa.test")

public class BootjpaexampleApplication{

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BootjpaexampleApplication.class, args);
		
		UserRepository userRepository = context.getBean(UserRepository.class);
		
		User user = new User();
		
		user.setName("Soumik Bose");
		
		user.setCity("Kolkata");
		
		user.setStatus("static");
		
		User user1 = userRepository.save(user);
		
	   System.out.println("User is == "+ user1);
	}

}
