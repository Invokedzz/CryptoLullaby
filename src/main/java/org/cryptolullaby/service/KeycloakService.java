package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.UserNotCreatedInKeycloakException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakService {

    @Value("${keycloak.realm.name}")
    private String realm;

    private final Keycloak keycloak;

    //private final UserRepresentation userRepresentation;

    //private final CredentialRepresentation credentialRepresentation;

    private static final boolean IS_ENABLED = true;

    private static final boolean IS_TEMPORARY = false;

    private static final String CREDENTIAL_TYPE = CredentialRepresentation.PASSWORD;

    public KeycloakService (Keycloak keycloak) {

        this.keycloak = keycloak;

      //  this.userRepresentation = userRepresentation;

        //this.credentialRepresentation = credentialRepresentation;

    }

    public void save (Users user) {

        var response = keycloak.realm(realm)
                    .users()
                    .create(setupUserProperties(user));

        //var token = keycloak.tokenManager().getAccessToken();
       // System.out.println("Access token: " + token.getToken());

        System.out.println("STATUS: " + response.getStatus());
        System.out.println("ERRO: " + response.readEntity(String.class));


    }

    private UserRepresentation setupUserProperties (Users user) {

        var rep = new UserRepresentation();

        rep.setUsername(user.getUsername());
        rep.setEmail(user.getEmail());
        rep.setEnabled(IS_ENABLED);

        var credentials = setupUserCredentials(user.getPassword());

        rep.setCredentials(List.of(credentials));
        rep.setRealmRoles(List.of("view-profile"));

        return rep;

    }

    private CredentialRepresentation setupUserCredentials (String password) {

        var cred = new CredentialRepresentation();

        cred.setTemporary(IS_TEMPORARY);
        cred.setType(CREDENTIAL_TYPE);
        cred.setValue(password);

        return cred;

    }

}
