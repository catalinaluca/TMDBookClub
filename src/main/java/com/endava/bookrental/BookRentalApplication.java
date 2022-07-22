package com.endava.bookrental;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


@SpringBootApplication
public class BookRentalApplication {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Bean
    UserDetailsManager userDetailsManager(){
        return new InMemoryUserDetailsManager();
    }

    @Bean
    InitializingBean initializingBean(UserDetailsManager manager){

        return ()->{
            UserDetails eu= User.withUsername("eu").password(passwordEncoder.encode("eu")).roles("USER").build();
            manager.createUser(eu);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(BookRentalApplication.class, args);
    }

}
