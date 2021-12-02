package cz.dsw.app_events_guide.event;

public class SupervisionEvent extends CustomEvent {

    public enum Severity {
        TRACE,
        INFO,
        WARNING,
        ERROR
    }

    private final Severity severity;
    private final String message;

    public SupervisionEvent(Object source, Severity severity, String message) {
        super(source);
        this.severity = severity;
        this.message = message;
    }

    @Override
    public String getEvent() { return super.getEvent() + "." + this.severity.name(); }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }
}
