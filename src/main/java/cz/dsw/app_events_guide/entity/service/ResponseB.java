package cz.dsw.app_events_guide.entity.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import cz.dsw.app_events_guide.entity.Response;

import java.beans.ConstructorProperties;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

public class ResponseB extends Response {

    public static final String AUDIT_EVENT = "provider.serviceB";

    private String text;

    @JsonCreator
    @ConstructorProperties({"nid", "name"})
    public ResponseB(URI nid, String name) {
        super(nid, name);
    }

    public ResponseB(URI nid, String name, URI tid) {
        super(nid, name, tid);
    }

    public ResponseB(URI nid, String name, URI tid, Date ts) {
        super(nid, name, tid, ts);
    }

    public String getText() { return Objects.toString(text); }
    public void setText(String text) { this.text = text; }

    @Override
    public String toString() {
        return MessageFormat.format("ResponseB(text={0}, {1})", text, super.toString());
    }

    public String auditEvent() { return AUDIT_EVENT; }
}
