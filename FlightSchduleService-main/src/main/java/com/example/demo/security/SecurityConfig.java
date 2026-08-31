package com.example.demo.security;

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
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
 
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()
                        
                        .requestMatchers("/admin2/schedules/**").hasRole("ADMIN")
                        .requestMatchers("/api/employee/**").hasRole("employee")
                        .requestMatchers(
                        	    "/swagger-ui/**",
                        	    "/swagger-ui.html",
                        	    "/v3/api-docs/**",
                        	    "/v3/api-docs",
                        	    "/webjars/**"
                        	).permitAll()
                        .anyRequest()
                        .authenticated())
                .httpBasic(basic -> {});
 
        return http.build();
    }
 
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
 
        UserDetails mike = User.builder()
                .username("admin1")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN", "employee")
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
