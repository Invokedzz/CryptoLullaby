package org.cryptolullaby.infra.security;

import org.cryptolullaby.entity.Users;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeycloakUserCredentials {

    private static final boolean IS_ENABLED = true;

    private static final boolean IS_TEMPORARY = false;

    private static final String CREDENTIAL_TYPE = CredentialRepresentation.PASSWORD;

    public UserRepresentation setupUserRepresentationAlongsideItsCredentials (Users user) {

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

        return cred;

    }

}
