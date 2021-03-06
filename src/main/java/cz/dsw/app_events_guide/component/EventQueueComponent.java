package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.event.CustomEvent;
import cz.dsw.app_events_guide.event.DelayedCustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.DelayQueue;

@Configuration
@EnableAsync
@Profile("listener-delayed")
public class EventQueueComponent {

    private static final Logger logger = LoggerFactory.getLogger(EventQueueComponent.class);

    @Bean
    public DelayQueue<DelayedCustomEvent<? extends CustomEvent>> eventQueue() {
        return new DelayQueue<>();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Async
    public void runEventConsumer(@Autowired DelayQueue<DelayedCustomEvent<? extends CustomEvent>> eventQueue) {
        logger.info("*** EventQueue Cleaner started ***");
        while (true) {
            try {
                eventQueue.take();
            } catch (InterruptedException ignored) { }
        }
    }
}
