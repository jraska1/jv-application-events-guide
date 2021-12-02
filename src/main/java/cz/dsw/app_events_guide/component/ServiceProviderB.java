package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.entity.ResponseCodeType;
import cz.dsw.app_events_guide.entity.service.RequestB;
import cz.dsw.app_events_guide.entity.service.ResponseB;
import cz.dsw.app_events_guide.event.ProviderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderB implements ServiceProvider<RequestB, ResponseB> {

    @Autowired private TokenFactory factory;
    @Autowired private ApplicationEventPublisher publisher;

    @Override
    public ResponseB perform(RequestB request) {

        publisher.publishEvent(new ProviderEvent(this, ProviderEvent.Phase.ASKED, request));

        ResponseB response = factory.tokenInstance(request.getTid(), ResponseB.class);
        response.setCode(ResponseCodeType.OK);
        response.setText("text length: " + request.getText().length());

        publisher.publishEvent(new ProviderEvent(this, ProviderEvent.Phase.ANSWERED, request, response));

        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider B";
    }
}
