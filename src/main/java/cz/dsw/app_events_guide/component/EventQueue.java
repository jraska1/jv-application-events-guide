package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.event.CustomEvent;
import cz.dsw.app_events_guide.event.DelayedCustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.DelayQueue;

@Configuration
@EnableAsync
@Profile("listener-delayed")
public class EventQueue extends DelayQueue<DelayedCustomEvent<? extends CustomEvent>> {

    private static final Logger logger = LoggerFactory.getLogger(EventQueue.class);

    @SuppressWarnings("InfiniteLoopStatement")
    @Async
    public void runEventConsumer() {
        logger.info("*** EventQueue Cleaner started ***");
        while (true) {
            try {
                take();
            } catch (InterruptedException ignored) {  }

        }
    }
}
