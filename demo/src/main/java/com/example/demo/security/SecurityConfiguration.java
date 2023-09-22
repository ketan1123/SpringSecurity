package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration  {
//	
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}
	
	
	@Bean
	public UserDetailsService userDetailService() {
		
		UserDetails normaluser = User.withDefaultPasswordEncoder()
				.username("Ketan")
				.password("Password")
				.roles("Normal").build();
		
		UserDetails adminuser=User
				.withDefaultPasswordEncoder()
				.username("Ketan")
				.password("Password")
				.roles("Admin").build();
		
		return new InMemoryUserDetailsManager(normaluser, adminuser);
			
	}
	
	
	
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
		.requestMatchers("/app/")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		
		return http.build();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// 		http
// 			.authorizeHttpRequests((authorizeHttpRequests) ->
// 				authorizeHttpRequests
// 					.requestMatchers("/**").hasRole("USER")
// 			)
// 			.formLogin(withDefaults());
// 		return http.build();
// 	}
//
// 	@Bean
// 	public UserDetailsService userDetailsService() {
// 		UserDetails user = User.withDefaultPasswordEncoder()
// 			.username("user")
// 			.password("user")
// 			.roles("USER")
// 			.build();
// 		UserDetails admin = User.withDefaultPasswordEncoder()
// 			.username("admin")
// 			.password("admin")
// 			.roles("ADMIN", "USER")
// 			.build();
// 		return new InMemoryUserDetailsManager(user, admin);
// 	}
//
//	private Customizer<FormLoginConfigurer<HttpSecurity>> withDefaults() {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
