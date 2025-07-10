package org.cryptolullaby.infra.security;

import org.cryptolullaby.model.enums.RolesName;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

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

        Set <String> roleNames = Set.of(USER_ROLE);

        assignRoles(userResource, roleNames);

    }

    public void assignModeratorRole (UserResource userResource) {

        Set <String> roleNames = Set.of(USER_ROLE, MODERATOR_ROLE);

        assignRoles(userResource, roleNames);

    }

    public void assignAdminRole (UserResource userResource) {

        Set <String> roleNames = Set.of(USER_ROLE, ADMIN_ROLE);

        assignRoles(userResource, roleNames);

    }

    private void assignRoles (UserResource userResource, Set <String> roleNames) {

        List <RoleRepresentation> roleRepresentationList = getRoleRepresentationList();

        roleRepresentationList.forEach(System.out::println);

        if (!roleRepresentationList.isEmpty()) {

            List <RoleRepresentation> userRolesList = new ArrayList<>();

            for (RoleRepresentation role : roleRepresentationList) {

                if (roleNames.contains(role.getName())) {

                    userRolesList.add(role);

                }

            }

            addRoleToCollection(userRolesList, userResource);

        }

    }

    private List <RoleRepresentation> getRoleRepresentationList () {

        return keycloak.realm(realm).roles().list();

    }

    private void addRoleToCollection (List <RoleRepresentation> userRolesList, UserResource userResource) {

        System.out.println("Roles: " + userRolesList.stream().map(RoleRepresentation::getName).toList());

        userResource.roles().realmLevel().add(userRolesList);

    }

}
