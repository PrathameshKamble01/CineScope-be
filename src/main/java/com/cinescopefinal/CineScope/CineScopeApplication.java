package com.cinescopefinal.CineScope;

import com.cinescopefinal.CineScope.entities.enums.Role;
import com.cinescopefinal.CineScope.entities.enums.Status;
import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.entities.enums.Subscription;
import com.cinescopefinal.CineScope.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableMethodSecurity
public class CineScopeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(CineScopeApplication.class, args);
	}

	public void run(String... args){
		Users adminAccount = usersRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			Users users = new Users();

			users.setEmail("admin@gmail.com");
			users.setName("admin");
			users.setRole(Role.ADMIN);
			users.setPassword(new BCryptPasswordEncoder().encode("admin"));
			users.setSubscription(Subscription.PREMIMUM);
			users.setMovieTypes("1,2,3,4,5");
			users.setStatus(Status.ACTIVE);
			usersRepository.saveAndFlush(users);
			System.out.println("Admin user created.");
		}
		else {
			System.out.println("Admin user already exists.");
		}
	}

}
