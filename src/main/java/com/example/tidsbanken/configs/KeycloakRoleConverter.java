package com.example.tidsbanken.configs;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    /**
     *
     * @param jwt the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return
     */
    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
        final Map<String, Object> claims = jwt.getClaims();
        final Map<String, Map<String, List<String>>> resourceAccess =
                (Map<String, Map<String, List<String>>>) claims.getOrDefault("resource_access", emptyMap());

        Map<String, List<String>> backendRoles = resourceAccess.getOrDefault("backend", emptyMap());

        return backendRoles.getOrDefault("roles", emptyList()).stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
