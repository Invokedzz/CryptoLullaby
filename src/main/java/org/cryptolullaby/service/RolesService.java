package org.cryptolullaby.service;

import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.model.enums.RolesName;

public interface RolesService {

    Roles createIfRoleNotExistOrElseReturnIt (RolesName name);

}
