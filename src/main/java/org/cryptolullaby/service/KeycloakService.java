package org.cryptolullaby.service;

import jakarta.ws.rs.core.Response;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.*;
import org.cryptolullaby.infra.security.KeycloakUserCredentials;
import org.cryptolullaby.infra.security.KeycloakUserRoles;
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

    private final KeycloakUserRoles keycloakUserRoles;

    public KeycloakService (Keycloak keycloak, KeycloakUserCredentials keycloakUserCredentials, KeycloakUserRoles keycloakUserRoles) {

        this.keycloak = keycloak;

        this.keycloakUserCredentials = keycloakUserCredentials;

        this.keycloakUserRoles = keycloakUserRoles;

    }

    public String save (Users user) {

        var response = createUserInKeycloakWithItsDefaultCredentials(user);

        switch (response.getStatus()) {

            case 201 -> {

                var userKeycloakId = searchUserByEmailThenReturnTheUserId(user);

                assignProperRoleInKeycloakToUser(user, userKeycloakId);

                return userKeycloakId;

            }

            case 401 -> throw new UnauthorizedRequestException("Unauthorized: invalid credentials!");

            case 404 -> throw new ResourceNotFoundException("Current URL not found!");

            case 502 -> throw new BadGatewayException("Bad Gateway!");

            case 504 -> throw new GatewayTimeoutException("Gateway timeout!");

            default -> throw new InternalServerException("Server internal error!");

        }

    }

    private void assignProperRoleInKeycloakToUser (Users user, String userId) {

        var lookForUser = keycloak.realm(realm).users().get(userId);

        System.out.println(lookForUser);

    }

    private Response createUserInKeycloakWithItsDefaultCredentials (Users user) {

        return keycloak
                .realm(realm).users()
                .create(userRepresentationAlongsideCredentials(user));

    }

    private String searchUserByEmailThenReturnTheUserId (Users user) {

        var users = keycloak.
                realm(realm).users()
                .searchByEmail(user.getEmail(), true);

        return users.getFirst().getId();

    }

    private UserRepresentation userRepresentationAlongsideCredentials (Users user) {

        return keycloakUserCredentials.setupUserRepresentationAlongsideItsCredentials(user);

    }

}
