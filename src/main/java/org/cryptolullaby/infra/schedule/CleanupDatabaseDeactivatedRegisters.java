package org.cryptolullaby.infra.schedule;

import org.cryptolullaby.service.ScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanupDatabaseDeactivatedRegisters {

    private final ScheduleService scheduleService;

    private static final String MIDNIGHT_DECEMBER_25TH = "0 0 0 25 12 ?";

    private static final String TIMEZONE_SOUTH_AMERICA = "America/Sao_Paulo";

    public CleanupDatabaseDeactivatedRegisters(ScheduleService scheduleService) {

        this.scheduleService = scheduleService;

    }

    @Scheduled(cron = MIDNIGHT_DECEMBER_25TH, zone = TIMEZONE_SOUTH_AMERICA)
    public void cleanupInactiveUsersFromTheDatabaseInDecember() {

        scheduleService.scheduleDateToDeleteDeactivatedUsers();

    }

}