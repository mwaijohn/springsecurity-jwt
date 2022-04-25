package com.mwaiauth.springjwt;

import com.mwaiauth.springjwt.model.Role;
import com.mwaiauth.springjwt.model.User;
import com.mwaiauth.springjwt.service.UserService;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringjwtApplication.class, args);
	}
        
        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        @Bean
        CommandLineRunner run(UserService userService){
            return args -> {
                userService.SaveRole(new Role(null,"ROLE_USER"));
                userService.SaveRole(new Role(null,"ROLE_MANAGER"));
                userService.SaveRole(new Role(null,"ROLE_ADMIN"));
                userService.SaveRole(new Role(null,"ROLE_SUPER_ADMIN"));
                
                userService.saveUser(new User(null,"John Mwai","john","1234",new ArrayList<>()));
                userService.saveUser(new User(null,"Mary Asego","mary","1234",new ArrayList<>()));
                userService.saveUser(new User(null,"Martin Marion","martin","1234",new ArrayList<>()));
                userService.saveUser(new User(null,"Joseph Jackson","joseph","1234",new ArrayList<>()));
                
                userService.addRoleToUser("john", "ROLE_USER");
                userService.addRoleToUser("john", "ROLE_MANAGER");
                userService.addRoleToUser("john", "ROLE_ADMIN");
                
                userService.addRoleToUser("mary", "ROLE_ADMIN");
                userService.addRoleToUser("mary", "ROLE_SUPER_ADMIN");
                userService.addRoleToUser("martin", "ROLE_USER");
                
                userService.addRoleToUser("joseph", "ROLE_USER");

            };
        }

}
