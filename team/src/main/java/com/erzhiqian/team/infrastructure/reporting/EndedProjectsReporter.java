package com.erzhiqian.team.infrastructure.reporting;

import com.erzhiqian.team.domain.value.project.EndedProject;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
@Log4j2
public class EndedProjectsReporter {

    private RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(200))
            .setReadTimeout(Duration.ofMillis(2000))
            .build();

    @Async
    @EventListener
    public void report(EndedProject endedProject) {
        log.info(" start report.");
        try {
            restTemplate.postForObject("http://localhost:8081/reports/projects", endedProject, String.class);
        } catch (RestClientException ex) {
            log.error("Error reporting ended project", ex);
        }
    }
}
