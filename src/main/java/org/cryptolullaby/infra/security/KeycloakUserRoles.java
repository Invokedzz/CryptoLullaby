package org.cryptolullaby.infra.security;

import org.cryptolullaby.model.enums.RolesName;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class KeycloakUserRoles {

    @Value("${keycloak.realm.name}")
    private String realm;

    private final Keycloak keycloak;

    private static final String USER_ROLE = RolesName.USER.name();

    private static final String MODERATOR_ROLE = RolesName.MODERATOR.name();

    private static final String ADMIN_ROLE = RolesName.ADMIN.name();

    public KeycloakUserRoles (Keycloak keycloak) {

        this.keycloak = keycloak;

    }

    public void assignUserRole (UserResource userResource) {

        RoleRepresentation roleRepresentation = keycloak.realm(realm).roles().get(USER_ROLE).toRepresentation();

        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));

    }

    public void assignModeratorRole (UserResource userResource) {

        RoleRepresentation roleRepresentation = keycloak.realm(realm).roles().get(MODERATOR_ROLE).toRepresentation();

        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));

    }

    public void assignAdminRole (UserResource userResource) {

        RoleRepresentation roleRepresentation = keycloak.realm(realm).roles().get(ADMIN_ROLE).toRepresentation();

        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));

    }

}
