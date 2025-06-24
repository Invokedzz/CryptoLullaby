package org.cryptolullaby.service;

import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.model.enums.RolesName;
import org.cryptolullaby.repository.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    private static final RolesName USER_ROLE = RolesName.USER;

    private static final RolesName MODERATOR_ROLE = RolesName.MODERATOR;

    private static final RolesName ADMIN_ROLE = RolesName.ADMIN;

    public RolesService (RolesRepository rolesRepository) {

        this.rolesRepository = rolesRepository;

    }

    public Roles getDefaultRole () {

        return rolesRepository
                .findByName(USER_ROLE)
                .orElseGet(() -> rolesRepository.save(new Roles(USER_ROLE)));

    }

    public Roles getModRole () {

        return rolesRepository
                .findByName(MODERATOR_ROLE)
                .orElseGet(() -> rolesRepository.save(new Roles(MODERATOR_ROLE)));

    }

    public Roles getAdminRole () {

        return rolesRepository
                .findByName(ADMIN_ROLE)
                .orElseGet(() -> rolesRepository.save(new Roles(ADMIN_ROLE)));

    }

}
