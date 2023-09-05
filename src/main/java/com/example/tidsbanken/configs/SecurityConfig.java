package com.example.tidsbanken.configs;
import lombok.RequiredArgsConstructor;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;
    public static String ADMIN  ="client_admin";
    public static  String USER = "client_user";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/tidsbanken/public").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tidsbanken/public-1").hasRole(USER)
                        .requestMatchers(HttpMethod.GET, "/tidsbanken/public-2").hasRole(ADMIN)
                        .requestMatchers(request -> request.getRequestURI().startsWith("/swagger")).permitAll()
                        .anyRequest().authenticated()
                );

        http.
                oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)
                ));
        http.
                sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}


