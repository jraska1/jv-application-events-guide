package cz.dsw.app_events_guide.rest;

import cz.dsw.app_events_guide.event.CustomEvent;
import cz.dsw.app_events_guide.event.DelayedCustomEvent;
import org.apache.commons.math3.stat.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.DelayQueue;
import java.util.stream.IntStream;

@RestController
@Profile("listener-delayed")
public class EventsController {

    @Autowired private DelayQueue<DelayedCustomEvent<? extends CustomEvent>> eventQueue;

    @RequestMapping(value = "/events/all", method = {RequestMethod.GET, RequestMethod.POST})
    public DelayQueue<DelayedCustomEvent<? extends CustomEvent>> eventsAll() {
        return eventQueue;
    }

    @RequestMapping(value = "/events/histogram", method = {RequestMethod.GET, RequestMethod.POST})
    public long[] eventsHistogram() {
        Frequency frequency = new Frequency();
        eventQueue.forEach(event -> frequency.addValue(Long.valueOf(Math.max(System.currentTimeMillis() - event.getEvent().getTimestamp(), 0) / 10000).intValue()));
        return IntStream.range(0, 6).asLongStream().map(frequency::getCount).toArray();
    }
}
