package org.cryptolullaby.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {

    @Value("${keycloak.realm.name}")
    private String realm;

    @Value("${keycloak.server.url}")
    private String serverUrl;

    @Value("${keycloak.client.id}")
    private String clientId;

    @Value("${keycloak.client.secret}")
    private String clientSecret;

    @Bean
    public Keycloak getKeycloak() {

        return KeycloakBuilder
                .builder()
                .realm(realm)
                .serverUrl(serverUrl)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorization(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();

    }

    /*@Bean
    public UserRepresentation userRepresentation () {

        return new UserRepresentation();

    }

    @Bean
    public CredentialRepresentation credentialRepresentation () {

        return new CredentialRepresentation();

    } */

}
