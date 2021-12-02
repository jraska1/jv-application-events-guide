package cz.dsw.app_events_guide.listener;

import cz.dsw.app_events_guide.event.ApplicantEvent;
import cz.dsw.app_events_guide.event.ProviderEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Profile("listener-simple")
public class SimpleEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEventListener.class);

    @EventListener
    @Order(10)
    public void handleApplicantEvent(ApplicantEvent event) {
        logger.info("Applicant Event handled: {} by {} at {}",
                StringUtils.rightPad(event.getPhase().toString(), 10),
                event.getSource().getClass().getSimpleName(),
                new SimpleDateFormat("HH:mm:ss").format(new Date(event.getTimestamp())));
    }

    @EventListener
    @Order(10)
    public void handleProviderEvent(ProviderEvent event) {
        logger.info("Provider Event handled:  {} by {} at {}",
                StringUtils.rightPad(event.getPhase().toString(), 10),
                event.getSource().getClass().getSimpleName(),
                new SimpleDateFormat("HH:mm:ss").format(new Date(event.getTimestamp())));
    }
}
