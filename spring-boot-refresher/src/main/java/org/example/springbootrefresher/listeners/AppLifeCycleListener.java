package org.example.springbootrefresher.listeners;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/*
 * Key lifecycle events
 * - ApplicationStartingEvent
 * - ApplicationReadyEvent
 * - ApplicationEnvironmentPreparedEvent
 * - ApplicationStartedEvent
 * - ApplicationFailedEvent
 */
@Component
public class AppLifeCycleListener implements ApplicationListener<ApplicationEvent> {


    //    @EventListener(ApplicationStartingEvent.class)
//    public void appStarting() {
//        System.err.println("Application is starting.");
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void appReady() {
//        System.err.println("Application ready.");
//    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationStartingEvent startingEvent) {
            System.err.println("ApplicationStartingEvent");
        } else if (event instanceof ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
            System.err.println("ApplicationEnvironmentPreparedEvent");
        }
    }


}
