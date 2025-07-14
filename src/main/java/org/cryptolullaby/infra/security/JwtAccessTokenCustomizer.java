package org.cryptolullaby.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAccessTokenCustomizer {

    private final ObjectMapper objectMapper;

    public JwtAccessTokenCustomizer (ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;

    }

}
