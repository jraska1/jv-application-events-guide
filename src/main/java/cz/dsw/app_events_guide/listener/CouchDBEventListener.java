package cz.dsw.app_events_guide.listener;

import cz.dsw.app_events_guide.event.CustomEvent;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@EnableAsync
@Profile("listener-couchdb")
public class CouchDBEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CouchDBEventListener.class);

    private final RestTemplate restTemplate;

    @Value("${couchdb.url-base}")
    private URI resourceBase;
    @Value("${couchdb.username}")
    private String userName;
    @Value("${couchdb.password}")
    private String password;

    public CouchDBEventListener(@Autowired RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    @EventListener
    public void handleEvent(CustomEvent event) {
        UUID id = UUID.randomUUID();

        URI path = resourceBase.resolve(resourceBase.getPath() + "/" + id);
        ResponseEntity<CouchDBRestResponse> response = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<>(event, createHeaders(userName, password)), CouchDBRestResponse.class);
        if (response.getStatusCode().is2xxSuccessful())
            if (response.getBody() != null)
                logger.info("Event stored to CouchDB: id={} rev={}", response.getBody().getId(), response.getBody().getRev());
            else
                logger.info("Event stored to CouchDB with no response object");
        else
            if (response.getBody() != null)
                logger.info("FAILED: error={}, reason={}", response.getBody().getError(), response.getBody().getReason());
            else
                logger.info("FAILED with no response object");
    }

    private HttpHeaders createHeaders(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        headers.set("Authorization", "Basic " + new String(encodedAuth));
        return headers;
    }
}
