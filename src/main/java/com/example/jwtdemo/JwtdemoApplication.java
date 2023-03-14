package com.example.jwtdemo;

import com.example.jwtdemo.entity.UserNew;
import com.example.jwtdemo.repo.UserNewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtdemoApplication {

	@Autowired
	private UserNewRepository repository;

	@PostConstruct
	public void initUsers() {
		List<UserNew> users = Stream.of(
				new UserNew(101, "ankit", "password", "ankit@gmail.com","8218814497","15/12/1999",1),
				new UserNew(102, "user1", "pwd1", "user1@gmail.com","8218814432","15/12/2000",1),
				new UserNew(103, "user2", "pwd2", "user2@gmail.com","8218814444","15/12/1990",1),
				new UserNew(104, "user3", "pwd3", "user3@gmail.com","8218814496","15/12/1980",1)
		).collect(Collectors.toList());
		repository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(JwtdemoApplication.class, args);
	}
}
