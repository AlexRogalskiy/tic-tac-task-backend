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

    public static class RefreshCounter {
        private final AtomicLong counter = new AtomicLong(0L);

        @EventListener
        public void onRefresh(RefreshScopeRefreshedEvent e) {
            System.out.println("Refreshed " + counter.incrementAndGet() + " times");
        }
    }
}
