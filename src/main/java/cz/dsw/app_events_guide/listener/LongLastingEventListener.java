package cz.dsw.app_events_guide.listener;

import cz.dsw.app_events_guide.event.ProviderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
//@EnableAsync
@Profile("listener-long")
public class LongLastingEventListener {

    private static final Logger logger = LoggerFactory.getLogger(LongLastingEventListener.class);

//    @Async
    @EventListener
    @Order(30)
    public void handleEvent(ProviderEvent event) {
        logger.info("Provider Event handled started ...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Exception occurred", e);
        }
        logger.info("Provider Event handled finished ...");
    }
}
