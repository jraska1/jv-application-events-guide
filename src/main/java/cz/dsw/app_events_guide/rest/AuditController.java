package cz.dsw.app_events_guide.rest;

import cz.dsw.app_events_guide.component.ProviderAuditRepository;
import cz.dsw.app_events_guide.entity.audit.AuditRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Profile("listener-jpa")
public class AuditController {

    @Autowired private ProviderAuditRepository repo;

    @RequestMapping(value = "/audit", method = {RequestMethod.GET, RequestMethod.POST})
    public List<AuditRecord> findAll() {
        List<AuditRecord> response = new ArrayList<>();
        repo.findAll().forEach(response::add);
        return response;
    }
}
