package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.infra.security.KeycloakUserCredentials;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {

    @Value("${keycloak.realm.name}")
    private String realm;

    private final Keycloak keycloak;

    private final KeycloakUserCredentials keycloakUserCredentials;

    public KeycloakService (Keycloak keycloak, KeycloakUserCredentials keycloakUserCredentials) {

        this.keycloak = keycloak;

        this.keycloakUserCredentials = keycloakUserCredentials;

    }

    public String save (Users user) {

        var response = keycloak
                        .realm(realm).users()
                        .create(userRepresentationAlongsideCredentials(user));

        if (response.getStatus() == 201) {

            var users = keycloak.
                        realm(realm).users()
                        .searchByEmail(user.getEmail(), true);

            for (UserRepresentation userRep : users) {

                System.out.println(userRep);

            }

            return users.getFirst().getId();

        }

        return "";

    }

    private UserRepresentation userRepresentationAlongsideCredentials (Users user) {

        return keycloakUserCredentials.setupUserRepresentationAlongsideItsCredentials(user);

    }

}
