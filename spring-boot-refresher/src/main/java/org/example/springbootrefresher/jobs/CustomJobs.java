package org.example.springbootrefresher.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CustomJobs {

    // Allows you to schedule as task
    // Use @EnableScheduling to enable this
    // Use @Scheduled on the method that needs to run as a job
    // - fixedRate      Runs at constant interval
    // - fixedDelay     Waits until completion plus delay
    // - cron           Runs at specific time/date
    // Spring uses quartz like cron
    // * * * * * *
    // Second       [0-59]
    // Minute       [0-56]
    // Hour         [0-23]
    // Day-Of-Month [1-31]
    // Month        [1-12]
    // Day-Of-Week  [0-6]

    // Example Cron
    // 0 0 1 * * *      ->  Daily 1 AM
    // 0 30 23 * * *    ->  Daily 11:30 PM
    @Scheduled(fixedRate = 5000)
    public void runJob() {
        System.err.println("Job running at : " + LocalTime.now());
    }
}
