package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.scheduler;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input.ScheduledJob;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@Configuration
@EnableScheduling
public class AccountActivationScheduler {
    private final ScheduledJob scheduledJob;

    public AccountActivationScheduler(final ScheduledJob scheduledJob) {
        this.scheduledJob = scheduledJob;
    }
    @Scheduled(cron = "${cron.expression}")
    public void activateAccountCronJob(){
        scheduledJob.activateAccountSchedule();
    }
}
