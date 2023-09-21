package com.tidsbanken.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable());
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/v1/resources/public").permitAll()
                        .requestMatchers("/api/v1/resources/restricted").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/api/v1/resources/admin").hasRole("ADMIN")
                        .requestMatchers("/api/v1/requests/**").permitAll()
                        .requestMatchers("/api/v1/responses/**").permitAll()
                        .requestMatchers("/api/v1/blocked-periods/**").permitAll()
                        .requestMatchers("/api/v1/employees/**").permitAll()
                        .requestMatchers("/api/v1/resources/public", "/swagger-ui.html", "/swagger-ui/**", "/v1/api-docs", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }
}

