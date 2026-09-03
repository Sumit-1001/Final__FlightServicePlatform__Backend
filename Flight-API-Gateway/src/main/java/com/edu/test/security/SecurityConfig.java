package com.edu.test.security;

import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(
    		
    		PasswordEncoder encoder) {

        UserDetails FlightAdmin = User.builder()
                .username("admin1")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails ScheduleAdmin = User.builder()
                .username("admin1")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(FlightAdmin, ScheduleAdmin );
    }
    
   

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(Customizer.withDefaults())
                .authorizeExchange(exchanges -> exchanges

                        .pathMatchers("/eureka/**").permitAll()

                        .pathMatchers("/api/public/**").permitAll()

                        .pathMatchers("/swagger-ui/**").permitAll()
                        .pathMatchers("/v3/api-docs/**").permitAll()
                        .pathMatchers("api/public/bookings/**").permitAll()
                        .pathMatchers("bookings/**").permitAll()
                        .pathMatchers(
                        	    HttpMethod.GET,
                        	    "/admin2/schedules/flight/**"
                        	).permitAll()
                        .pathMatchers("/admin2/**").hasRole("ADMIN")
                        
                        .pathMatchers(HttpMethod.GET,
                                "/flights/allFlights",
                                "/flights/source/**",
                                "/flights/destination/**",
                                "/flights/search/**",
                                "/flights/*"
                        ).permitAll()
                        .pathMatchers("/flights/addFlight/**").hasRole("ADMIN")
                        .pathMatchers("/flights/update/**").hasRole("ADMIN")
                        .pathMatchers("/flights/deleteFlight/**").hasRole("ADMIN")

                        .anyExchange().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://localhost:5173"
        ));

        configuration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    
  

}
