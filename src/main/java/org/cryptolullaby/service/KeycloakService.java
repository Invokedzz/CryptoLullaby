package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.UserNotCreatedInKeycloakException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    }

    public void save (Users user) {

        var response = keycloak.realm(realm)
                    .users()
                    .create(setupUserProperties(user));

        System.out.println(response);

        System.out.println(response.getStatus());

        if (response.getStatus() == 201) {

            System.out.println("Success!");

            return;

        }

        System.out.println("Erro DE NOVO HAHAHAHAHAHAH");

    }

    private UserRepresentation setupUserProperties (Users user) {

        var rep = new UserRepresentation();

        rep.setUsername(user.getUsername());
        rep.setEmail(user.getEmail());
        rep.setEnabled(IS_ENABLED);
        rep.setEmailVerified(true);

        var credentials = setupUserCredentials(user.getPassword());

        List <CredentialRepresentation> representationList = new ArrayList<>();
        representationList.add(credentials);
        rep.setCredentials(representationList);

        return rep;

    }

    private CredentialRepresentation setupUserCredentials (String password) {

        var cred = new CredentialRepresentation();

        cred.setTemporary(IS_TEMPORARY);
        cred.setType(CREDENTIAL_TYPE);
        cred.setValue(password);
        cred.setType(CredentialRepresentation.PASSWORD);

        return cred;

    }

}
