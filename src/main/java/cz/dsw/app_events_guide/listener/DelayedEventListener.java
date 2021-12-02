package cz.dsw.app_events_guide.listener;

import cz.dsw.app_events_guide.event.CustomEvent;
import cz.dsw.app_events_guide.event.DelayedCustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.DelayQueue;

@Service
@Profile("listener-delayed")
public class DelayedEventListener {

    @Value("${events.delay:5000}")
    private long delay;

    @Autowired private DelayQueue<DelayedCustomEvent<? extends CustomEvent>> eventQueue;

    @EventListener
    public void handleEvent(CustomEvent event) {
        eventQueue.put(new DelayedCustomEvent<>(event, delay));
    }
}
