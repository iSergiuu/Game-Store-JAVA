package com.gamestore.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Dezactivam CSRF pentru Postman
                .authorizeHttpRequests(auth -> auth
                        // 1. Oricine poate da Register sau vedea jocurile
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/games").permitAll()

                        // 2. Doar ADMIN poate modifica jocurile
                        .requestMatchers("/games/**").hasRole("ADMIN")

                        // 3. Doar userii logati pot cumpara
                        .requestMatchers("/shop/**").authenticated()
                        .requestMatchers("/users/**").authenticated() // Ca sa vezi lista de useri

                        // Orice altceva trebuie sa fie logat
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Activam Basic Auth (pentru Postman)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Algoritmul de criptare
    }
}