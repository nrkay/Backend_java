package com.challenge_8.challenge_8.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulerConfig {
    
    @Scheduled(cron = "* */1 * * * *")
    public void scheduleFixedDelayTask() {
        log.warn("FROM SCHEDULER");
    }
}
