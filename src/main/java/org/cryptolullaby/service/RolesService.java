package org.cryptolullaby.service;

import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.model.enums.RolesName;
import org.cryptolullaby.repository.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService (RolesRepository rolesRepository) {

        this.rolesRepository = rolesRepository;

    }

    public Roles getDefaultRole () {

        return rolesRepository
                .findByName(RolesName.USER)
                .orElseGet(() -> rolesRepository.save(new Roles(RolesName.USER)));

    }

}
