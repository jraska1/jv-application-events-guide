package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.entity.ResponseCodeType;
import cz.dsw.app_events_guide.entity.service.RequestA;
import cz.dsw.app_events_guide.entity.service.ResponseA;
import cz.dsw.app_events_guide.event.ProviderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServiceProviderA implements ServiceProvider<RequestA, ResponseA> {

    @Autowired private TokenFactory factory;
    @Autowired private ApplicationEventPublisher publisher;

    @Override
    public ResponseA perform(RequestA request) {

        publisher.publishEvent(new ProviderEvent(this, ProviderEvent.Phase.ASKED, request));

        ResponseA response = factory.tokenInstance(request.getTid(), ResponseA.class);
        response.setCode(ResponseCodeType.OK);
        response.setResult(request.getValue() + new Random().nextInt((int) Math.max(request.getValue() / 2, 10)));

        publisher.publishEvent(new ProviderEvent(this, ProviderEvent.Phase.ANSWERED, request, response));

        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider A";
    }
}
