package cz.dsw.app_events_guide.rest;

import cz.dsw.app_events_guide.component.ServiceProvider;
import cz.dsw.app_events_guide.entity.service.RequestA;
import cz.dsw.app_events_guide.entity.service.RequestB;
import cz.dsw.app_events_guide.entity.service.ResponseA;
import cz.dsw.app_events_guide.entity.service.ResponseB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired private ServiceProvider<RequestA, ResponseA> providerA;
    @Autowired private ServiceProvider<RequestB, ResponseB> providerB;

    @RequestMapping(value = "/rest/serviceA", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseA restProviderA(@RequestBody RequestA request) {
        return providerA.perform(request);
    }

    @RequestMapping(value = "/rest/serviceB", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseB restProviderB(@RequestBody RequestB request) {
        return providerB.perform(request);
    }
}
