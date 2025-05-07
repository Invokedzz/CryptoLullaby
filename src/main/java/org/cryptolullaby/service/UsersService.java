package org.cryptolullaby.service;

import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.UserNotFoundException;
import org.cryptolullaby.model.dto.RegisterDTO;
import org.cryptolullaby.model.dto.UpdateProfileDTO;
import org.cryptolullaby.model.enums.InterestName;
import org.cryptolullaby.model.enums.RolesName;
import org.cryptolullaby.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final RolesService rolesService;

    public UsersService (UsersRepository usersRepository, RolesService rolesService) {

        this.usersRepository = usersRepository;

        this.rolesService = rolesService;

    }

    public void createUser (RegisterDTO register) {

        if (!register.password().equals(register.repeatPassword())) {

        }

        var user = new Users(register);

        List <Roles> roles = List.of(rolesService.createIfRoleNotExistOrElseReturnIt(RolesName.USER));

        user.setRolesId(
                roles
                .stream()
                .map(Roles::getId)
                .collect(Collectors.toList())
        );

        usersRepository.save(user);

    }

    public Users findProfileById (@PathVariable String id) {

        return findUserById(id);

    }

    public void editProfileById (String id, UpdateProfileDTO profileDTO) {

        var user = findUserById(id);

        var sanitizedList = sanitizeInterestList(profileDTO.interests());

        var profile = new UpdateProfileDTO(

                profileDTO.email(),
                profileDTO.password(),
                profileDTO.confirmNewPassword(),
                sanitizedList

        );

        user.updateProfile(profile);

        usersRepository.save(user);

    }

    public void deactivateProfileById (@PathVariable String id) {

        var user = findUserById(id);

        user.deactivate();

        usersRepository.save(user);

    }

    private Users findUserById (String id) {

        return usersRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

    }

    private List <Interest> sanitizeInterestList (List <Interest> interests) {

        var sanitizedList =
                interests.stream()
                .filter(i -> i.getType() != null && !i.getType().getLabel().isBlank())
                .collect(Collectors.toList());

        if (sanitizedList.isEmpty()) {

            sanitizedList.add(new Interest(InterestName.NONE));

        }

        return sanitizedList;

    }

}
