package cz.dsw.app_events_guide.listener;

import cz.dsw.app_events_guide.component.ProviderAuditRepository;
import cz.dsw.app_events_guide.component.ServiceProvider;
import cz.dsw.app_events_guide.entity.Request;
import cz.dsw.app_events_guide.entity.Response;
import cz.dsw.app_events_guide.entity.audit.ProviderAuditRecord;
import cz.dsw.app_events_guide.event.ProviderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@Profile("listener-jpa")
public class JPAEventListener {

    private static final Logger logger = LoggerFactory.getLogger(JPAEventListener.class);

    @Autowired private ProviderAuditRepository repo;

    @Async
    @EventListener
    public void handleEvent(ProviderEvent event) {
        if (event.getPhase() == ProviderEvent.Phase.ANSWERED) {
            ProviderAuditRecord record = new ProviderAuditRecord("service.provider.answer", event.getRequest().getTid());
            record.setRequestFrom(event.getRequest().getName());
            record.setProviderName(((ServiceProvider<? extends Request, ? extends Response>)event.getSource()).getInstanceName());
            if (event.getResponse() != null)
                record.setCode(event.getResponse().getCode());
            repo.save(record);
            logger.info("ProviderAuditRecord was written to the database ...");
        }
    }
}
