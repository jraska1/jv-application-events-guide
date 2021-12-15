package cz.dsw.app_events_guide;

import cz.dsw.app_events_guide.component.EventQueueComponent;
import cz.dsw.app_events_guide.event.CustomEvent;
import cz.dsw.app_events_guide.event.DelayedCustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.util.concurrent.DelayQueue;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(@Nullable EventQueueComponent eventQueueComponent, @Nullable DelayQueue<DelayedCustomEvent<? extends CustomEvent>> eventQueue) {
        return args -> {
            logger.info("*** Hello World, greetings from Dwarf ***");
            if (eventQueueComponent != null)
                eventQueueComponent.runEventConsumer(eventQueue);
        };
    }
}
