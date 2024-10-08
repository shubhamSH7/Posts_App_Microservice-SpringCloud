package com.accounts.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class Config {

	@Bean
	SecurityFilterChain chain(HttpSecurity http) throws Exception {
		http.cors(c -> c.disable())
			.authorizeHttpRequests( req -> req.anyRequest().permitAll() );
			http.csrf(csr -> csr.disable());
		return http.build();
				
	} 
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
