package cz.dsw.app_events_guide.event;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedCustomEvent<T extends CustomEvent> implements Delayed {

    private final T event;
    private final long startTime;

    public DelayedCustomEvent(T event, long startTime) {
        this.event = event;
        this.startTime = System.currentTimeMillis() + startTime;
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        return Long.valueOf(this.startTime - ((DelayedCustomEvent<? extends CustomEvent>) delayed).startTime).intValue();
    }

    public T getEvent() {
        return event;
    }

    public long getStartTime() {
        return startTime;
    }
}
