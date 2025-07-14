package org.cryptolullaby.orchestration;

import org.cryptolullaby.orchestration.usecases.users.PermissionsUseCase;
import org.springframework.stereotype.Service;

@Service
public class PermissionsOrchestrationFacade {

    private final PermissionsUseCase permissionsUseCase;

    public PermissionsOrchestrationFacade (PermissionsUseCase permissionsUseCase) {

        this.permissionsUseCase = permissionsUseCase;

    }

    public void addACertainRoleToAnUserById (String id) {

        permissionsUseCase.addACertainRoleToAnUserById(id);

    }

}
