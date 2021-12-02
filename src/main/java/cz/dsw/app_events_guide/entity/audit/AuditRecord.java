package cz.dsw.app_events_guide.entity.audit;

import javax.persistence.*;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Entity
public class AuditRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private UUID oid;

    @Column(nullable = false)
    private String event;

    @Column
    private URI tid;

    @Column(nullable = false)
    private Date ts;

    public AuditRecord() {
        this(null, null);
    }

    public AuditRecord(String event) {
        this(event, null);
    }

    public AuditRecord(String event, URI tid) {
        this.oid = UUID.randomUUID();
        this.event = event;
        this.tid = tid;
        this.ts = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getOid() {
        return oid;
    }

    public void setOid(UUID oid) {
        this.oid = oid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public URI getTid() {
        return tid;
    }

    public void setTid(URI tid) {
        this.tid = tid;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
