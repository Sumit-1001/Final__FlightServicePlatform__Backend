package com.edu.test.security;




	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.core.userdetails.User;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.provisioning.InMemoryUserDetailsManager;
	import org.springframework.security.web.SecurityFilterChain;

	@Configuration
	@EnableMethodSecurity
	public class SecurityConfig {

	    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http)
	            throws Exception {

	        http
	                .csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/api/public/**").hasRole("user")
	                        .requestMatchers("/api/admin1/**").hasRole("admin")
	                        .requestMatchers("/api/admin2/**").hasRole("employee")
	                        .anyRequest().authenticated())
	                .httpBasic(basic -> {});

	        return http.build();
	    }

	    @Bean InMemoryUserDetailsManager userDetailsService() {

	        UserDetails mike = User.builder()
	                .username("admin1")
	                .password(passwordEncoder().encode("admin123"))
	                .roles("user")
	                .build();

	        UserDetails jenny = User.builder()
	                .username("jenny")
	                .password(passwordEncoder().encode("jenny"))
	                .roles("admin")
	                .build();

	        UserDetails neha = User.builder()
	                .username("neha")
	                .password(passwordEncoder().encode("neha123"))
	                .roles("employee")
	                .build();

	        return new InMemoryUserDetailsManager(mike, jenny, neha);
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	}

