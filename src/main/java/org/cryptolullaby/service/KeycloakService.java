package org.cryptolullaby.service;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {

    private final UserRepresentation userRepresentation;

    private final CredentialRepresentation credentialRepresentation;

    public KeycloakService (UserRepresentation userRepresentation, CredentialRepresentation credentialRepresentation) {

        this.userRepresentation = userRepresentation;

        this.credentialRepresentation = credentialRepresentation;

    }

    public void save (User user) {}

}
