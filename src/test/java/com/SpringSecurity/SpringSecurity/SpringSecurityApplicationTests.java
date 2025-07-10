package com.SpringSecurity.SpringSecurity;

import com.SpringSecurity.SpringSecurity.entities.User;
import com.SpringSecurity.SpringSecurity.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {
	@Autowired
	private JwtService service;
	@Test
	void contextLoads() {
		User user = new User(41L, "abc@gmail.com","1234");
		String token = service.generateToken(user);
		System.out.println(token);
	}
}
