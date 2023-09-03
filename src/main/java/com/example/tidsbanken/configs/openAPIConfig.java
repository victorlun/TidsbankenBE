package com.example.tidsbanken.configs;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "keycloak_implicit", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(authorizationUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/auth", tokenUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token", scopes = {
                @OAuthScope(name = "openid", description = "OpenID Connect Endpoints"),
                @OAuthScope(name = "profile", description = "Standard profile claims excluding phone and email"),
                @OAuthScope(name = "email", description = "Include email address information in profile"),
                @OAuthScope(name = "groups", description = "Group membership information"),
        }))
)
public class openAPIConfig {
}
