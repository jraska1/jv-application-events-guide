package cz.dsw.app_events_guide.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dsw.app_events_guide.event.CustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Profile("listener-json")
public class JsonEventListener {

    private static final Logger logger = LoggerFactory.getLogger(JsonEventListener.class);

    @Autowired private ObjectMapper objectMapper;

    @EventListener
    @Order(20)
    public void handleEvent(CustomEvent event) {
        try {
            logger.info("Custom Event {} handled: JSON DATA: \n{}", event.getClass().getSimpleName(), objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(event));
        } catch (JsonProcessingException e) {
            logger.error("Object to JSON mapping failed!", e);
        }
    }
}
