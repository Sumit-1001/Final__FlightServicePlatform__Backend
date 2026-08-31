package com.flight.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin1 = User.builder()
                .username("admin1")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails admin2 = User.builder()
                .username("admin2")
                .password(passwordEncoder().encode("admin456"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin1, admin2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Swagger
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/v3/api-docs",
                        "/webjars/**"
                ).permitAll()

                // Public Flight APIs
                .requestMatchers(HttpMethod.GET,
                        "/flights/allFlights",
                        "/flights/source/**",
                        "/flights/destination/**",
                        "/flights/search/**",
                        "/flights/*"
                ).permitAll()

                // Admin APIs
                .requestMatchers(HttpMethod.POST,
                        "/flights/addFlight")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT,
                        "/flights/update/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE,
                        "/flights/deleteFlight/**")
                .hasRole("ADMIN")

                .anyRequest()
                .authenticated()
            )

            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}