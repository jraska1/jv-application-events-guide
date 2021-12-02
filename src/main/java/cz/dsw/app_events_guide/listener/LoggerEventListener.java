package cz.dsw.app_events_guide.listener;

import cz.dsw.app_events_guide.event.CustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Profile("listener-logger")
public class LoggerEventListener {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEventListener.class);

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @EventListener
    @Order(0)
    public void handleEvent(CustomEvent event) {
        logger.info("Custom Event {} handled at {}", event.getSource().getClass().getSimpleName(), dateFormatter.format(new Date(event.getTimestamp())));
    }
}
