package cz.dsw.app_events_guide.component;

import cz.dsw.app_events_guide.entity.service.RequestA;
import cz.dsw.app_events_guide.entity.service.RequestB;
import cz.dsw.app_events_guide.entity.service.ResponseA;
import cz.dsw.app_events_guide.entity.service.ResponseB;
import cz.dsw.app_events_guide.event.ApplicantEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@Profile("applicant")
@EnableScheduling
public class ServiceApplicant {

    private final RestTemplate restTemplate;
    private static final Random rand = new Random();

    @Value("${applicant.resource-base}")
    private String resourceBase;

    @Autowired private TokenFactory factory;
    @Autowired private ApplicationEventPublisher publisher;

    public ServiceApplicant(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void executeApplicant() {
        if (rand.nextBoolean()) {
            RequestA request = factory.tokenInstance(RequestA.class);
            request.setValue(12345);

            publisher.publishEvent(new ApplicantEvent(this, ApplicantEvent.Phase.REQUESTED, request));

            ResponseEntity<ResponseA> response = restTemplate.postForEntity(resourceBase + "/serviceA", request, ResponseA.class);
            if (response.getStatusCode().equals(HttpStatus.OK))
                publisher.publishEvent(new ApplicantEvent(this, ApplicantEvent.Phase.RECEIVED, request, response.getBody()));
        }
        else {
            RequestB request = factory.tokenInstance(RequestB.class);
            request.setText("Haf haf haf");

            publisher.publishEvent(new ApplicantEvent(this, ApplicantEvent.Phase.REQUESTED, request));

            ResponseEntity<ResponseB> response = restTemplate.postForEntity(resourceBase + "/serviceB", request, ResponseB.class);
            if (response.getStatusCode().equals(HttpStatus.OK))
                publisher.publishEvent(new ApplicantEvent(this, ApplicantEvent.Phase.RECEIVED, request, response.getBody()));
        }
    }
}
