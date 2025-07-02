package org.cryptolullaby.infra.schedule;

import org.cryptolullaby.service.ScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanupDatabaseDeactivatedRegisters {

    private final ScheduleService scheduleService;

    public CleanupDatabaseDeactivatedRegisters (ScheduleService scheduleService) {

        this.scheduleService = scheduleService;

    }

    @Scheduled(cron = "0 0 0 25 12 ?", zone = "America/Sao_Paulo")
    public void cleanupInactiveUsersFromTheDatabaseInDecember () {

        scheduleService.scheduleDateToDeleteDeactivatedUsers();

    }

}
