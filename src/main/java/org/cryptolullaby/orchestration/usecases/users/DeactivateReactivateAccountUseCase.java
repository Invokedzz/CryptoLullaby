package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.users.EmailResponseDTO;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class DeactivateReactivateAccountUseCase {

    private final UsersService usersService;

    public DeactivateReactivateAccountUseCase (UsersService usersService) {

        this.usersService = usersService;

    }

    public void reactivateUserAccount (EmailResponseDTO emailResponseDTO) {

        var user = findUserByEmail(
                emailResponseDTO.email(),
                false
        );

        user.activate();

        saveChangesInTheDatabase(user);

    }

    public void deactivateUserAccount (EmailResponseDTO emailResponseDTO) {

        var user = findUserByEmail(
                emailResponseDTO.email(),
                true
        );

        user.deactivate();

        saveChangesInTheDatabase(user);

    }

    private void saveChangesInTheDatabase (Users user) {

        usersService.save(user);

    }

    private Users findUserByEmail (String email, boolean isActive) {

        return usersService.findUserByEmailAndIsActive(email, isActive);

    }

}
