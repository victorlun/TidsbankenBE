package com.example.tidsbanken.configs;

import lombok.RequiredArgsConstructor;

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
   // public static final String ADMIN = "client_admin";
   // public static final String USER = "client_user";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(authorize -> {authorize
                        .requestMatchers(HttpMethod.GET, "/tidsbanken-docs","/tidsbanken-docs/").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/employees/public1").hasRole(ADMIN)
                       // .requestMatchers(HttpMethod.GET, "/employees/public2").hasRole(USER)
                        //.requestMatchers(HttpMethod.GET, "/employees/public3").hasAnyRole(ADMIN, USER)
                       // .requestMatchers(request -> request.getRequestURI().startsWith("/swagger")).permitAll()
                        .anyRequest().authenticated();
    });
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





