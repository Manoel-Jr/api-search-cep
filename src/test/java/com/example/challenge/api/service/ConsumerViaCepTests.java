package com.example.challenge.api.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class ConsumerViaCepTests {

    public static final String URL = "https://viacep.com.br/ws/58100683/json";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConsumerViaCep consumerViaCep;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        restTemplate = Mockito.spy(restTemplate);
        consumerViaCep = Mockito.spy(consumerViaCep);
    }

    @Test
    void deveConsultarApiViaCepComSucesso() {
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, null, responseType);
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);
        org.junit.jupiter.api.Assertions.assertNotNull(response.getBody(), "body não deve ser Nulo");
    }
}
