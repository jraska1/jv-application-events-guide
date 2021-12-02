package cz.dsw.app_events_guide.listener;

import cz.dsw.app_events_guide.event.SupervisionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Profile("listener-application")
public class ApplicationEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);

    @EventListener(classes = {ApplicationReadyEvent.class, ContextClosedEvent.class})
    public ApplicationEvent handleEvent(ApplicationEvent event) {

        if (event instanceof ApplicationReadyEvent)
            return new SupervisionEvent(this, SupervisionEvent.Severity.INFO, "Application started");
        else if (event instanceof ContextClosedEvent)
            return new SupervisionEvent(this, SupervisionEvent.Severity.INFO, "Application stopped");
        else
            logger.error("ApplicationEvent Class {} is not supported!", event.getClass().getSimpleName());
        return null;
    }
}
