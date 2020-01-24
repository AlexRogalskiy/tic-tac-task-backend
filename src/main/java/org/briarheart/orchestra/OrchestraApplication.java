package org.briarheart.orchestra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class OrchestraApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrchestraApplication.class, args);
    }

    /**
     * This bean demonstrates using of configuration properties stored in the GitHub repository. Properties are
     * loaded by separate Spring Boot application - configuration server that looks for properties file named
     * according to the value of 'spring.application.name' parameter from bootstrap.yml. Library
     * 'org.springframework.cloud:spring-cloud-config-client' is responsible for handling communication between client
     * and configuration server.
     */
    @Bean
    public CommandLineRunner projectNamePrinter(@Value("${application.name:n/a}") String projectName) {
        return args -> {
            System.out.println("Project name: " + projectName);
        };
    }

    @Bean
    public RefreshCounter refreshCounter() {
        return new RefreshCounter();
    }

    /**
     * This class demonstrates ability to handle refresh events that might be triggered to reload configuration
     * properties.
     */
    public static class RefreshCounter {
        private final AtomicLong counter = new AtomicLong(0L);

        @EventListener
        public void onRefresh(RefreshScopeRefreshedEvent e) {
            System.out.println("Refreshed " + counter.incrementAndGet() + " times");
        }
    }
}
