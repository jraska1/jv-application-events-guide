package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.entity.Request;
import cz.dsw.app_events_guide.entity.Response;

public interface ServiceProvider <R extends Request, T extends Response> {

    T perform(R request);

    default String getInstanceName() {
        return "Provider";
    }
}
