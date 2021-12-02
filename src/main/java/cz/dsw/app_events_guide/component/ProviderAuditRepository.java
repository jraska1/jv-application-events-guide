package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.entity.audit.ProviderAuditRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("listener-jpa")
public interface ProviderAuditRepository extends CrudRepository<ProviderAuditRecord, Long> { }
