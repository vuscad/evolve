package com.objective.evolve;

import com.objective.evolve.model.Domain;
import com.objective.evolve.model.Task;
import com.objective.evolve.repository.DomainRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DomainHandler {

    private final String DOMAIN_ID = "123456";
    private final String DOMAIN_NAME = "Domain name";
    private final String TASK_ID = "321654";
    private final String TASK_NAME = "Task name";

    @Autowired
    private WebTestClient testClient;
    @MockBean
    private DomainRepository domainRepository;
    private Domain domain = new Domain(DOMAIN_ID, DOMAIN_NAME, Collections.singletonList(new Task(TASK_ID, TASK_NAME, new ArrayList<>())));

    @Before
    public void setup() {
        when(domainRepository.findAll()).thenReturn(Flux.just(domain));
        when(domainRepository.findById(anyString())).thenReturn(Mono.just(domain));
        when(domainRepository.save(domain)).thenReturn(Mono.just(domain));
    }

    @Test
    public void getAllDomains() {
        testClient.get()
                .uri("/domains")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Domain.class).hasSize(1);
    }

    @Test
    public void getDomainById() {
        testClient.get()
                .uri("/domain/" + DOMAIN_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Domain.class).hasSize(1);
    }
}
