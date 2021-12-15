package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.DelayQueue;

@Service
@EnableScheduling
@Profile("event-checker")
public class EventChecker {

    @Autowired private ApplicationEventPublisher publisher;
    @Autowired @Nullable private DelayQueue<DelayedCustomEvent<? extends CustomEvent>> eventQueue;

    @Value("${event.checker.interval:10000}")
    private int interval;
    @Value("${event.checker.threshold:10}")
    private int threshold;

    @Async
    @Scheduled(fixedRateString = "${event.checker.scheduled:5000}")
    public void check() {
        if (eventQueue != null) {
            long eventCount = eventQueue.stream()
                    .filter(event -> Long.valueOf(Math.max(System.currentTimeMillis() - event.getEvent().getTimestamp(), 0)).intValue() <= interval)
                    .filter(event -> event.getEvent() instanceof ProviderEvent || event.getEvent() instanceof ApplicantEvent)
                    .count();
            if (eventCount >= threshold) {
                String message = MessageFormat.format("Events Count exceeded specified threshold {0} in last {1} milliseconds!", threshold, interval);
                publisher.publishEvent(new SupervisionEvent(this, SupervisionEvent.Severity.WARNING, message));
            }
        }
    }
}
