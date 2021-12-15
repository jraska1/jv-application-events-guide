package cz.dsw.app_events_guide.listener;

import java.util.UUID;

public class CouchDBRestResponse {

    private Boolean ok;
    private UUID id;
    private String rev;
    private String error;
    private String reason;

    public Boolean getOk() {
        return ok;
    }
    public void setOk(Boolean ok) {
        this.ok = ok;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getRev() {
        return rev;
    }
    public void setRev(String rev) {
        this.rev = rev;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}
