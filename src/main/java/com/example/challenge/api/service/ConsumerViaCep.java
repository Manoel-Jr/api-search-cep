package com.example.challenge.api.service;

import com.example.challenge.api.exception.CepInvalidoException;
import com.example.challenge.api.exception.CepNaoEncontradoException;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerViaCep {

    @Value("${url.ws.viacep}")
    private String urlviacep;

    @Autowired
    private RestTemplate restTemplate;

    public String consumirWebServiceViaCep(String cep) {
        validaCep(cep);
        String url = urlviacep + cep + "/json";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        String body = response.getBody();

        if (!StringUtils.isBlank(body)) {
            verificaIsCepExists(body);
        }
        return body;
    }

    private void verificaIsCepExists(String body) {
        boolean hasErro = false;

        if (body.contains("erro")) {
            JSONObject json = new JSONObject(body);
            hasErro = json.getBoolean("erro");
            if (hasErro) {
                throw new CepNaoEncontradoException();
            }
        }
    }

    private void validaCep(String cep) {
        if (cep == null || cep.length() != 8) {
            throw new CepInvalidoException();
        }
    }


}
