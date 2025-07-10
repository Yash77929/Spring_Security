package com.SpringSecurity.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/posts").permitAll() // Public
                        .requestMatchers("/posts/**").hasAnyRole("ADMIN") // ADMIN only
                        .anyRequest().authenticated() // All other requests require login
                )
                .formLogin(Customizer.withDefaults()); // Enable default login page

        return http.build();
    }

    // In-memory user setup
    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        UserDetails normalUser = User.withUsername("user")
                .password(passwordEncoder().encode("User@123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder().encode("Admin@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}