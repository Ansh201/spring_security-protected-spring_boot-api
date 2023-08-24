package com.learn;

import com.learn.models.User;
import com.learn.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityLearnApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityLearnApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User user=new User();
		user.setEmail("Ansh@gmail.com");
		user.setUsername("Ansh");
		user.setPassword(this.bCryptPasswordEncoder.encode("Ansh@123"));
		user.setRole("ROLE_NORMAL");
		this.userRepository.save(user);

		User user1=new User();
		user1.setEmail("Manish@gmail.com");
		user1.setUsername("Manish");
		user1.setPassword(this.bCryptPasswordEncoder.encode("Manish@123"));
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);
	}
}
