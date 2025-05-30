package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.model.enums.RolesName;
import org.cryptolullaby.repository.RolesRepository;
import org.cryptolullaby.service.RolesService;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository) {

        this.rolesRepository = rolesRepository;

    }

    public Roles createIfRoleNotExistOrElseReturnIt (RolesName name) {

        return rolesRepository.findByName(name)
                .orElseGet(() -> rolesRepository.save(new Roles(name)));

    }

}
