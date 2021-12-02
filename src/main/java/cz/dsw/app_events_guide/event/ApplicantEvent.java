package cz.dsw.app_events_guide.event;

import cz.dsw.app_events_guide.entity.Request;
import cz.dsw.app_events_guide.entity.Response;

public class ApplicantEvent extends CustomEvent {

    public enum Phase {
        REQUESTED,
        RECEIVED
    }

    private final Phase phase;
    private final Request request;
    private final Response response;

    public ApplicantEvent(Object source, Phase phase, Request request) {
        super(source);
        this.phase = phase;
        this.request = request;
        this.response = null;
    }

    public ApplicantEvent(Object source, Phase phase, Request request, Response response) {
        super(source);
        this.phase = phase;
        this.request = request;
        this.response = response;
    }

    public Phase getPhase() {
        return phase;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }
}
