package com.example.tidsbanken.configs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
@Getter
@Setter
public class JwtAuthConverterProperties {

  private String resourceId;
    private String principalAttribute;
}
