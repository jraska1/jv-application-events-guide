package cz.dsw.app_events_guide.entity.audit;

import cz.dsw.app_events_guide.entity.ResponseCodeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.net.URI;

@Entity
public class ProviderAuditRecord extends AuditRecord {

    @Column
    private String requestFrom;

    @Column
    private String providerName;

    @Column
    private ResponseCodeType code;

    public ProviderAuditRecord() {
    }

    public ProviderAuditRecord(String event) {
        super(event);
    }

    public ProviderAuditRecord(String event, URI tid) {
        super(event, tid);
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public ResponseCodeType getCode() {
        return code;
    }

    public void setCode(ResponseCodeType code) {
        this.code = code;
    }
}
