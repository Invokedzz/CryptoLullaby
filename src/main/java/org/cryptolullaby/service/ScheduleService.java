package org.cryptolullaby.service;

import org.cryptolullaby.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleService {

    private final UsersRepository usersRepository;

    private static final boolean IS_INACTIVE = false;

    public ScheduleService (UsersRepository usersRepository) {

        this.usersRepository = usersRepository;

    }

    public void scheduleDateToDeleteDeactivatedUsers () {

        var inactiveUsersIdList = usersRepository.findAllByIsActive(IS_INACTIVE);

        for (var inactiveUserId : inactiveUsersIdList) {

            System.out.println(inactiveUserId);

            if (inactiveUserId.createdAt().getYear() < LocalDateTime.now().getYear()) {

                System.out.println(inactiveUserId.id());

                usersRepository.deleteById(inactiveUserId.id());

            }

        }

    }

}
